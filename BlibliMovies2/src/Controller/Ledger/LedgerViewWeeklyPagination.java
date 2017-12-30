package Controller.Ledger;

import Model.Invoice;
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

@WebServlet("/admin/ledger/weekly/page")
public class LedgerViewWeeklyPagination extends HttpServlet {
    InvoiceService invoiceService = new InvoiceServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int page = (Integer.parseInt(request.getParameter("page")) - 1) * 10;
            List<Invoice> invoiceList = invoiceService.getWeeklyInvoice(request.getParameter("date"), (int)request.getSession().getAttribute("storeid"), page);

            Gson gson = new Gson();
            String json = gson.toJson(invoiceList);

            PrintWriter out = response.getWriter();
            out.print("{\"count\": " + 123 + ",");
            out.print(" \"result\" : " + json + "}");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
