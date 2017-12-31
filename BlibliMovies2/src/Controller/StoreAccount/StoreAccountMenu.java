package Controller.StoreAccount;

import DAO.StoreAccountDAO;
import Model.StoreAccount;
import Service.StoreAccountService;
import Service.StoreAccountServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/storeaccount")
public class StoreAccountMenu extends HttpServlet{
    StoreAccountService storeAccountService = new StoreAccountServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/database/storeaccount/storeaccount_menu.jsp";

        try{
            List<StoreAccount> storeAccounts = storeAccountService.getAllStoreAccount(0);
            int pageCounter = storeAccountService.getCountAllStoreAccount();

            request.setAttribute("storeaccounts", storeAccounts);
            request.setAttribute("page", pageCounter);
            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
