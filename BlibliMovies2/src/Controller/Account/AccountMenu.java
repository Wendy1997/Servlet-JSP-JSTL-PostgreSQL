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
    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String menuAccountAddress = "/view/database/account/account_menu.jsp";
    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";
    private final int initialPage = 0;

    /**
     * Sebuah method GET yang memberikan halaman list akun
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute(storeIdSession) == null){
            request.getRequestDispatcher(storeLoginAddress).forward(request, response);
        }
        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute(roleAccountSession) == null){
            request.getRequestDispatcher(accountLoginAddress).forward(request, response);
        }
        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute(roleAccountSession).equals(roleAdmin)){
            request.getRequestDispatcher(accountLoginAddress).forward(request, response);
        }


        try{
            // Pengambilan data list akun dan juga role akun untuk diretrieve pada menu
            List<Account> accounts = accountService.getAllAccount((int)request.getSession().getAttribute(storeIdSession), initialPage);
            List<AccountRole> accountRoles = accountService.getAllAccountRole((int)request.getSession().getAttribute(storeIdSession));

            // Pengambilan data jumlah halaman yang akan ditampilkan pada menu
            int pageCounter = accountService.getCountAllAccount((int)request.getSession().getAttribute(storeIdSession));

            request.setAttribute("roles", accountRoles);
            request.setAttribute("accounts", accounts);
            request.setAttribute("page", pageCounter);

            request.getRequestDispatcher(menuAccountAddress).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
