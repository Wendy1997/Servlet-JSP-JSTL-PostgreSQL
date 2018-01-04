package Controller.Login;

import Service.SuperAdminService;
import Service.SuperAdminServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Sebuah kelas yang menghandle superAdmin account login
 * url: /index
 */
@WebServlet("/super")
public class SuperAdmin extends HttpServlet {
    SuperAdminService superAdminService = new SuperAdminServiceDatabase();
    private final String superLoginAddress = "/view/login/superadmin_login.jsp";
    private final String successAddress = "/view/database/success.jsp";
    private final String superAdminSession = "superadminid";
    private final String title = "Login";
    private final String statusLoginBerhasil = "Success";
    private final String statusLoginGagal = "Failed";
    private final String link = "/supermenu";


    /**
     * Suatu Controller yang akan me redirect menuju halaman Super Admin Login dan melakukan logout
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // validasi Logout
        if(request.getParameter("page") != null){
            if(request.getParameter("page").equals("logout")){
                request.getSession().removeAttribute(superAdminSession);
            }
        }

        request.getRequestDispatcher(superLoginAddress).forward(request, response);
    }

    /**
     * Pengecekan apakah sesuai dengan database SuperAdmin
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // Inisialisasi akun
            Model.SuperAdmin superAdmin = superAdminService.getSuperAdmin(request.getParameter("username"));

            // Apakah akun tersedia?
            if(superAdmin != null){
                if(superAdmin.getPassword().equals(request.getParameter("password").hashCode() + "")) {

                    // Set session superAdminid dan superAdminname
                    request.getSession().setAttribute("superadminid", superAdmin.getId());

                    // Redirect menuju halaman success
                    request.setAttribute("title", title);
                    request.setAttribute("complete", statusLoginBerhasil);
                    request.setAttribute("link", link);

                } else{

                    // Redirect menuju halaman success
                    request.setAttribute("title", title);
                    request.setAttribute("complete", statusLoginGagal);
                    request.setAttribute("link", link);
                }
            } else {
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
