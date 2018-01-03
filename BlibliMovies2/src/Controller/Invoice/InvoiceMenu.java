package Controller.Invoice;

import Model.Invoice;
import Service.InvoiceService;
import Service.InvoiceServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Sebuah kelas yang menghandle list invoice
 * url: /admin/invoice
 */
@WebServlet("/admin/invoice")
public class InvoiceMenu extends HttpServlet {
    InvoiceService invoiceService = new InvoiceServiceDatabase();

    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String menuInvoiceAddress = "/view/database/invoice/invoice_menu.jsp";
    private final String successAddress = "/view/database/success.jsp";

    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";

    private final String title = "Account";
    private final String statusDeleteBerhasil = "Deleted";
    private final String statusRetrieveBerhasil = "Retrieved";
    private final String link = "admin";

    private final int initialPage = 0;

    /**
     * Sebuah method GET yang memberikan halaman list invoice
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute(storeIdSession) == null){
            request.getRequestDispatcher(storeLoginAddress).forward(request, response);
        }
        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute(roleAccountSession) == null){
            request.getRequestDispatcher(accountLoginAddress).forward(request, response);
        }
        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute(roleAccountSession).equals(roleAdmin)){
            request.getRequestDispatcher(accountLoginAddress).forward(request, response);
        }


        try {
            // Pengambilan data list invoice untuk diretrieve pada menu
            List<Invoice> invoices = invoiceService.getAllInvoice((int)request.getSession().getAttribute(storeIdSession), initialPage);

            // Pengambilan data jumlah halaman yang akan ditampilkan pada menu
            int pageCounter = invoiceService.getCountAllInvoice((int)request.getSession().getAttribute(storeIdSession));

            request.setAttribute("invoices", invoices);
            request.setAttribute("page", pageCounter);

            request.getRequestDispatcher(menuInvoiceAddress).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
