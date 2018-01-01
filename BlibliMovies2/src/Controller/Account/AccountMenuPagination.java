package Controller.Account;

import Model.Account;
import Service.AccountService;
import Service.AccountServiceDatabase;
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
 * Sebuah kelas yang menghandle pagination list akun
 * url: /admin/account/page
 */
@WebServlet("/admin/account/page")
public class AccountMenuPagination extends HttpServlet {
    AccountService accountService = new AccountServiceDatabase();

    /**
     * Sebuah method GET yang memberikan data list akun pada suatu halaman
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

            // Pengambilan list akun pada offset halaman tersebut
            List<Account> accounts = accountService.getAllAccount((int)request.getSession().getAttribute("storeid"), page);

            // Inisialisasi dan mengubah objek menjadi JSON
            Gson gson = new Gson();
            String json = gson.toJson(accounts);

            // Pengiriman data menuju AJAX
            PrintWriter out = response.getWriter();
            out.print("{\"count\": " + 123 + ",");
            out.print(" \"result\" : " + json + "}");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
