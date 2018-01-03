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

    private final String superLoginAddress = "/view/login/superadmin_login.jsp";
    private final String successAddress = "/view/database/success.jsp";
    private final String menuStoreAccountAddress = "/view/database/storeaccount/storeaccount_menu.jsp";

    private final String superAdminSession = "superadminid";

    private final String title = "Account";
    private final String statusDeleteBerhasil = "Deleted";
    private final String statusRetrieveBerhasil = "Retrieved";
    private final String link = "admin";

    private final int initialPage = 0;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Validasi apakah sudah login as super
        if(request.getSession().getAttribute(superAdminSession) == null){
            request.getRequestDispatcher(superLoginAddress).forward(request, response);
        }

        try{
            List<StoreAccount> storeAccounts = storeAccountService.getAllStoreAccount(initialPage);
            int pageCounter = storeAccountService.getCountAllStoreAccount();

            request.setAttribute("storeaccounts", storeAccounts);
            request.setAttribute("page", pageCounter);
            request.getRequestDispatcher(menuStoreAccountAddress).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
