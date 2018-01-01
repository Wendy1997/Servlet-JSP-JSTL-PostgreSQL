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

    /**
     * Sebuah method GET yang akan menampilkan invoice setelah melakukan transaksi
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Initial Address
        String address = "/view/transaction/view_invoice.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storeid") == null){
            address = "/view/login/store_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute("role") == null){
            address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        try{
            // Pengambilan data invoice dan order details
            Invoice invoice = invoiceService.getInvoice(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));
            List<OrderDetail> orderDetails = invoiceService.getAllOrderDetail(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));

            // Pengecekan jika memiliki member
            if(invoice.getMemberId() != 0){
                Promo promo = invoiceService.getPromo((int)request.getSession().getAttribute("storeid"));
                request.setAttribute("promo", promo);
            }

            request.setAttribute("invoice", invoice);
            request.setAttribute("orderDetails", orderDetails);

            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

