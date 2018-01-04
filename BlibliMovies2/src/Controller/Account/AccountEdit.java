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
 * Sebuah kelas yang menghandle pengeditan akun
 * url: /admin/account/edit
 */
@WebServlet("/admin/account/edit")
public class AccountEdit extends HttpServlet{
    AccountService accountService = new AccountServiceDatabase();
    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String editAccountAddress = "/view/database/account/account_edit.jsp";
    private final String successAddress = "/view/database/success.jsp";
    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";
    private final String title = "Account";
    private final String statusEditBerhasil = "Updated";
    private final String link = "/admin/account";

    /**
     * Sebuah method GET yang memberikan halaman form edit akun
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

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

        try {

            // Pengambilan data akun yang ingin diedit agar langsung dapat di retrieve
            Account account = accountService.getAccount(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession));
            request.setAttribute("account", account);

            // Pengambilan data seluruh role akun untuk dimasukkan kedalam form
            List<AccountRole> accountRoleList = accountService.getAllAccountRoleTrue((int)request.getSession().getAttribute(storeIdSession));
            request.setAttribute("role", accountRoleList);
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(editAccountAddress).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman edit akun
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{

            if(request.getParameter("password").length() > 0){
                // Inisialisasi Account dari hasil form
                Account account = new Account( request.getParameter("username"),
                        (int)request.getSession().getAttribute(storeIdSession),
                        request.getParameter("password").hashCode() + "",
                        Integer.parseInt(request.getParameter("role")));

                // Sebuah method yang akan mengupdate akun ke dalam database
                accountService.updateAccout(account);
            } else {
                // Inisialisasi Account dari hasil form
                Account account = new Account( request.getParameter("username"),
                        (int)request.getSession().getAttribute(storeIdSession),
                        Integer.parseInt(request.getParameter("role")));

                // Sebuah method yang akan mengupdate akun ke dalam database
                accountService.updateAccoutWithoutPass(account);
            }

            // Redirect menuju halaman success
            request.setAttribute("title", title);
            request.setAttribute("complete", statusEditBerhasil);
            request.setAttribute("link", link);

            request.getRequestDispatcher(successAddress).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}