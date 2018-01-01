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

/**
 * Sebuah kelas yang menghandle list invoice pada setiap harinya
 * url: /admin/ledger/daily
 */
@WebServlet("/admin/ledger/daily")
public class LedgerViewDaily extends HttpServlet {
    InvoiceService invoiceService = new InvoiceServiceDatabase();

    /**
     * Sebuah method POST yang memberikan halaman list invoice pada setiap harinya
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            // Pengambilan data offset dan jumlah pendapatan pada hari tersebut
            List<Double> pageCounter = invoiceService.getCountDailyInvoice(request.getParameter("date"), (int)request.getSession().getAttribute("storeid"));

            // Pengambilan list invoice hari tersebut
            List<Invoice> invoiceList = invoiceService.getDailyInvoice(request.getParameter("date"), (int)request.getSession().getAttribute("storeid"), 0);

            // Inisialisasi dan mengubah objek menjadi JSON
            Gson gson = new Gson();
            String output = gson.toJson(invoiceList);

            // Pengiriman data menuju AJAX
            PrintWriter out = response.getWriter();
            out.print("{\"count\": " + pageCounter.get(0).intValue() + ",");
            out.print("\"sum\": " + pageCounter.get(1) + ",");
            out.print(" \"result\" : " + output + "}");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
