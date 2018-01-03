package Controller.Login;

import DAO.AccountDAO;
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

/**
 * Sebuah kelas yang menghandle account login dari store tersebut
 * url: /login
 */
@WebServlet("/login")
public class Account extends HttpServlet {
    AccountService accountService = new AccountServiceDatabase();

    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String successAddress = "/view/database/success.jsp";

    private final String storeIdSession = "storeid";
    private final String userIdSession = "userId";
    private final String userNameSession = "username";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";

    private final String title = "Login";
    private final String statusLoginBerhasil = "Success";
    private final String statusLoginGagal = "Failed";
    private final String link = "/login";

    /**
     * Sebuah method GET yang memberikan form login
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

        //Validasi jika ingin logout
        if(request.getParameter("page") != null){
            if(request.getParameter("page").equals("logout")){
                request.getSession().removeAttribute(roleAccountSession);
                request.getSession().removeAttribute(userNameSession);
                request.getSession().removeAttribute(userIdSession);
            }
        }

        request.getRequestDispatcher(accountLoginAddress).forward(request, response);
    }

    /**
     * Suatu pengecekan apakah form input terdapat pada database Account
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            // Inisialisasi akun
            Model.Account account = accountService.getAccountTrue(request.getParameter("username"), (int)request.getSession().getAttribute(storeIdSession));

            // Apakah akun tersedia?
            if(account != null){
                if(account.getPassword().equals(request.getParameter("password").hashCode() + "")){
                    AccountRole accountRole = accountService.getAccountRole(account.getRoleid(),(int)request.getSession().getAttribute(storeIdSession));

                    // Set session role, username, dan user id
                    request.getSession().setAttribute("role", accountRole.getRole());
                    request.getSession().setAttribute("username", account.getUsername());
                    request.getSession().setAttribute("userid", account.getid());

                    // Redirect menuju halaman success
                    request.setAttribute("title", title);
                    request.setAttribute("complete", statusLoginBerhasil);
                    request.setAttribute("link", "/" + (String)request.getSession().getAttribute(roleAccountSession));
                }else{

                    // Redirect menuju halaman success
                    request.setAttribute("title", title);
                    request.setAttribute("complete", statusLoginGagal);
                    request.setAttribute("link", link);
                }
            } else{

                // Redirect menuju halaman success
                request.setAttribute("title", title);
                request.setAttribute("complete", statusLoginGagal);
                request.setAttribute("link", link);
            }

            request.getRequestDispatcher(successAddress).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
