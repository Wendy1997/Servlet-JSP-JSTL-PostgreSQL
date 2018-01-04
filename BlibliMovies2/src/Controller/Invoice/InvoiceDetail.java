package Controller.Invoice;

import Model.Invoice;
import Model.OrderDetail;
import Model.Promo;
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
 * Sebuah kelas yang menghandle melihat detail dari invoice
 * url: /admin/invoice/detail
 */
@WebServlet("/admin/invoice/detail")
public class InvoiceDetail extends HttpServlet {
    InvoiceService invoiceService = new InvoiceServiceDatabase();
    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String detailInvoiceAddress = "/view/database/invoice/invoice_detail.jsp";
    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";

    /**
     * Sebuah method GET yang akan memberikan informasi detail mengenai invoice tersebut
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


        try{
            // Inisialisasi Invoice dan oreder details dari invoice tersebut
            Invoice invoice = invoiceService.getInvoice(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession));
            List<OrderDetail> orderDetails = invoiceService.getAllOrderDetail(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession));

            // Sebuah pengecekan apakah ia member atau bukan
            if(invoice.getMemberId() != 0){

                // Jika member maka ia akan memberikan promo
                Promo promo = invoiceService.getPromo((int)request.getSession().getAttribute(storeIdSession));
                request.setAttribute("promo", promo);
            }

            request.setAttribute("invoice", invoice);
            request.setAttribute("orderDetails", orderDetails);

            request.getRequestDispatcher(detailInvoiceAddress).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
