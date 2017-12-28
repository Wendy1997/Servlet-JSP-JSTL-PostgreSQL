package Controller.Ledger;

import Model.Invoice;
import Model.OrderDetail;
import Model.Promo;
import Service.InvoiceService;
import Service.InvoiceServiceDatabase;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/ledger/daily")
public class LedgerViewDaily extends HttpServlet {
    InvoiceService invoiceService = new InvoiceServiceDatabase();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            List<Invoice> invoiceList = invoiceService.getDailyInvoice(request.getParameter("date"), (int)request.getSession().getAttribute("storeid"));
            System.out.println(invoiceList.toString());
            Gson gson = new Gson();
            String output = gson.toJson(invoiceList);
            PrintWriter out = response.getWriter();
            out.print(output);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
