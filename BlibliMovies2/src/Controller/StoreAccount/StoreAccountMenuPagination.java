package Controller.StoreAccount;

import Model.StoreAccount;
import Service.StoreAccountService;
import Service.StoreAccountServiceDatabase;
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
import java.util.ServiceConfigurationError;

/**
 * Sebuah kelas yang menghandle pagination list store account
 * url: /admin/storeaccount/page
 */
@WebServlet("/admin/storeaccount/page")
public class StoreAccountMenuPagination extends HttpServlet {
    StoreAccountService storeAccountService = new StoreAccountServiceDatabase();

    /**
     * Sebuah method GET yang memberikan data list screening time pada suatu halaman
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            // Pengambilan data offset
            int page = (Integer.parseInt(request.getParameter("page")) - 1) * 10;

            // Pengambilan list store account pada offset halaman tersebut
            List<StoreAccount> storeAccounts = storeAccountService.getAllStoreAccount(page);

            // Inisialisasi dan mengubah objek menjadi JSON
            Gson gson = new Gson();
            String json = gson.toJson(storeAccounts);

            // Pengiriman data menuju AJAX
            PrintWriter out = response.getWriter();
            out.print("{\"result\" : " + json + "}");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
