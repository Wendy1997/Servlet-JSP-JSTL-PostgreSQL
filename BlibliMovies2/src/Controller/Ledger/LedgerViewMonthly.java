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
import java.util.Comparator;
import java.util.List;

/**
 * Sebuah kelas yang menghandle list invoice pada setiap bulannya
 * url: /admin/ledger/monthly
 */
@WebServlet("/admin/ledger/monthly")
public class LedgerViewMonthly extends HttpServlet {
    InvoiceService invoiceService = new InvoiceServiceDatabase();

    private final String storeIdSession = "storeid";
    private final int initialPage = 0;

    /**
     * Sebuah method POST yang memberikan halaman list invoice pada setiap bulannya
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            // Pengambilan data offset dan jumlah pendapatan pada bulan tersebut
            List<Double> pageCounter = invoiceService.getCountMonthlyInvoice(request.getParameter("date"), (int)request.getSession().getAttribute(storeIdSession));

            // Pengambilan list invoice bulan tersebut
            List<Invoice> invoiceList = invoiceService.getMonthlyInvoice(request.getParameter("date"), (int)request.getSession().getAttribute(storeIdSession), initialPage);
            invoiceList.sort(Comparator.comparingInt(Invoice::getId));

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
