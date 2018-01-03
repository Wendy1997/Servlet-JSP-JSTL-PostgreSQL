package Controller.Super;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Sebuah kelas yang menghandle menu super
 * url: /super
 */
@WebServlet("/supermenu")
public class SuperMenu extends HttpServlet{

    private final String superAdminLoginAddress = "/view/login/superadmin_login.jsp";
    private final String superMenuAddress = "/view/menu/super_menu.jsp";

    /**
     * Sebuah method GET yang memberikan halaman menu super
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Validasi apakah sudah login as super
        if(request.getSession().getAttribute("superadminid") == null){
            request.getRequestDispatcher(superAdminLoginAddress).forward(request, response);
        }

        request.getRequestDispatcher(superMenuAddress).forward(request, response);
    }
}
