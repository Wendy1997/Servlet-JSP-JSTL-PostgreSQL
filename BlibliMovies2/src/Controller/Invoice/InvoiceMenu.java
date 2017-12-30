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

@WebServlet("/admin/invoice")
public class InvoiceMenu extends HttpServlet {
    InvoiceService invoiceService = new InvoiceServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/invoice/invoice_menu.jsp";

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

        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute("role").equals("admin")){
            address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        try {
            List<Invoice> invoices = invoiceService.getAllInvoice((int)request.getSession().getAttribute("storeid"), 0);
            int pageCounter = invoiceService.getCountAllInvoice((int)request.getSession().getAttribute("storeid"));

            request.setAttribute("invoices", invoices);
            request.setAttribute("page", pageCounter);
            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
