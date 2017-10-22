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

        try {
            List<Invoice> invoices = invoiceService.getAllInvoice();

            request.setAttribute("invoices", invoices);
            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
