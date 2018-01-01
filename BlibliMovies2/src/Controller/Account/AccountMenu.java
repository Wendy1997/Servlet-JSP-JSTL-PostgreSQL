package Controller.Account;

import DAO.AccountDAO;
import Model.Account;
import Model.AccountRole;
import Service.AccountService;
import Service.AccountServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Sebuah kelas yang menghandle list akun
 * url: /admin/account
 */
@WebServlet("/admin/account")
public class AccountMenu extends HttpServlet{
    AccountService accountService = new AccountServiceDatabase();

    /**
     * Sebuah method GET yang memberikan halaman list akun
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Initial Address
        String address = "/view/database/account/account_menu.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storeid") == null){
            address = "/view/login/store_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute("role") == null){
            address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute("role").equals("admin")){
            address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        try{
            // Pengambilan data list akun dan juga role akun untuk diretrieve pada menu
            List<Account> accounts = accountService.getAllAccount((int)request.getSession().getAttribute("storeid"), 0);
            List<AccountRole> accountRoles = accountService.getAllAccountRole((int)request.getSession().getAttribute("storeid"));

            // Pengambilan data jumlah halaman yang akan ditampilkan pada menu
            int pageCounter = accountService.getCountAllAccount((int)request.getSession().getAttribute("storeid"));

            request.setAttribute("roles", accountRoles);
            request.setAttribute("accounts", accounts);
            request.setAttribute("page", pageCounter);

            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
