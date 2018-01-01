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

    /**
     * Sebuah method GET yang memberikan halaman form edit akun
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Initial Address
        String address = "/view/database/account/account_edit.jsp";

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

        try {

            // Pengambilan data akun yang ingin diedit agar langsung dapat di retrieve
            Account account = accountService.getAccount(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));
            request.setAttribute("account", account);

            // Pengambilan data seluruh role akun untuk dimasukkan kedalam form
            List<AccountRole> accountRoleList = accountService.getAllAccountRoleTrue((int)request.getSession().getAttribute("storeid"));
            request.setAttribute("role", accountRoleList);
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
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

            // Inisialisasi Account dari hasil form
            Account account = new Account( request.getParameter("username"),
                    (int)request.getSession().getAttribute("storeid"),
                    request.getParameter("password").hashCode() + "",
                    Integer.parseInt(request.getParameter("role")));

            // Sebuah method yang akan mengupdate akun ke dalam database
            accountService.updateAccout(account);

            // Redirect menuju halaman success
            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Account");
            request.setAttribute("complete", "Updated");
            request.setAttribute("link", "/admin/account");

            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}