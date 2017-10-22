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

@WebServlet("/admin/invoice/detail")
public class InvoiceDetail extends HttpServlet {
    InvoiceService invoiceService = new InvoiceServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/invoice/invoice_detail.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storename") == null){
            address = "/view/login/store_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute("role") == null){
            address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute("role").equals("admin")){
            address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        try{
            Invoice invoice = invoiceService.getInvoice(request.getParameter("id"), (String)request.getSession().getAttribute("storename"));
            List<OrderDetail> orderDetails = invoiceService.getAllOrderDetail(request.getParameter("id"), (String)request.getSession().getAttribute("storename"));

            if(invoice.getMemberId() != 0){
                Promo promo = invoiceService.getPromo("1", (String)request.getSession().getAttribute("storename"));
                request.setAttribute("promo", promo);
            }

            request.setAttribute("invoice", invoice);
            request.setAttribute("orderDetails", orderDetails);
            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
