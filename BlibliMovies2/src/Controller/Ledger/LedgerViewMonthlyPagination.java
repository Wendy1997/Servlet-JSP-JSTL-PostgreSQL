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
import java.util.Comparator;
import java.util.List;

/**
 * Sebuah kelas yang menghandle pagination list invoice pada bulan tersebut
 * url: /admin/ledger/monthly/page
 */
@WebServlet("/admin/ledger/monthly/page")
public class LedgerViewMonthlyPagination extends HttpServlet {
    InvoiceService invoiceService = new InvoiceServiceDatabase();

    private final String storeIdSession = "storeid";

    /**
     * Sebuah method GET yang memberikan data list invoice bulan tersebut pada suatu halaman
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            // Pengambilan data offset
            int page = (Integer.parseInt(request.getParameter("page")) - 1) * 10;

            // Pengambilan list invoice pada bulan tersebut pada offset halaman tersebut
            List<Invoice> invoiceList = invoiceService.getMonthlyInvoice(request.getParameter("date"), (int)request.getSession().getAttribute(storeIdSession), page);
            invoiceList.sort(Comparator.comparingInt(Invoice::getId));

            // Inisialisasi dan mengubah objek menjadi JSON
            Gson gson = new Gson();
            String json = gson.toJson(invoiceList);

            // Pengiriman data menuju AJAX
            PrintWriter out = response.getWriter();
            out.print("{\"count\": " + 123 + ",");
            out.print(" \"result\" : " + json + "}");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
