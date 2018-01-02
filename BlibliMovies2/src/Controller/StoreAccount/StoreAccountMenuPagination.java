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

@WebServlet("/admin/storeaccount/page")
public class StoreAccountMenuPagination extends HttpServlet {
    StoreAccountService storeAccountService = new StoreAccountServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            int page = (Integer.parseInt(request.getParameter("page")) - 1) * 10;
            List<StoreAccount> storeAccounts = storeAccountService.getAllStoreAccount(page);

            Gson gson = new Gson();
            String json = gson.toJson(storeAccounts);

            PrintWriter out = response.getWriter();
            out.print("{\"count\": " + 123 + ",");
            out.print(" \"result\" : " + json + "}");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
