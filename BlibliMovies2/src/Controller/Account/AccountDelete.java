package Controller.Account;

import DAO.AccountDAO;
import Model.Account;
import Service.AccountService;
import Service.AccountServiceDatabase;
import jdk.nashorn.internal.runtime.ECMAException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Sebuah kelas yang menghandle penghapusan ataupun pengembalian akun
 * url: /admin/account/delete
 */
@WebServlet("/admin/account/delete")
public class AccountDelete extends HttpServlet {
    AccountService accountService = new AccountServiceDatabase();

    /**
     * Sebuah method GET yang akan melakukan penghapusan ataupun pengembalian akun
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

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

            // Pengambilan data akun yang bersangkutan
            Account account = accountService.getAccount(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));

            // Pengecekan apakah status akun tersebut aktif atau tidak
            if(account.isStatus()){

                // Jika aktif maka akan didelete
                accountService.deleteAccount(account.getUsername() + "", (int)request.getSession().getAttribute("storeid"));
                request.setAttribute("complete", "Deleted");
            } else{

                // Jika pasif maka akan di retrieve
                accountService.retrieveAccount(account.getUsername() + "", (int)request.getSession().getAttribute("storeid"));
                request.setAttribute("complete", "Retrieved");
            }

            // Redirect menuju halaman success
            address = "/view/database/success.jsp";
            request.setAttribute("title", "Account");
            request.setAttribute("link", "/admin/account");

            request.getRequestDispatcher(address).forward(request, response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
