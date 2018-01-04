package Controller.Menu;

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
 * Sebuah kelas yang menghandle main menu untuk admin dan cashier
 * url: /menu
 */
@WebServlet("/menu")
public class Menu extends HttpServlet {
    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";

    /**
     * Sebuah method GET yang memberikan halaman main menu
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

        // Redirect apakah ia menuju admin atau cashier
        response.sendRedirect("/"+ request.getSession().getAttribute(roleAccountSession));
    }
}