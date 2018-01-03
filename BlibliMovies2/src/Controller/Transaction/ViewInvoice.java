package Controller.Transaction;

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
 * Sebuah kelas yang akan menampilkan invoice setelah melakukan transaksi
 */
@WebServlet("/cashier/invoice")
public class ViewInvoice extends HttpServlet {
    InvoiceService invoiceService = new InvoiceServiceDatabase();
    private final String viewInvoiceAddress = "/view/transaction/view_invoice.jsp";
    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";

    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";

    /**
     * Sebuah method GET yang akan menampilkan invoice setelah melakukan transaksi
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

        try{
            // Pengambilan data invoice dan order details
            Invoice invoice = invoiceService.getInvoice(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession));
            List<OrderDetail> orderDetails = invoiceService.getAllOrderDetail(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession));

            // Pengecekan jika memiliki member
            if(invoice.getMemberId() != 0){
                Promo promo = invoiceService.getPromo((int)request.getSession().getAttribute(storeIdSession));
                request.setAttribute("promo", promo);
            }

            request.setAttribute("invoice", invoice);
            request.setAttribute("orderDetails", orderDetails);

            request.getRequestDispatcher(viewInvoiceAddress).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

