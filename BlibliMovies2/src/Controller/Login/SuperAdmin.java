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

    /**
     * Suatu Controller yang akan me redirect menuju halaman Super Admin Login dan melakukan logout
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/login/superadmin_login.jsp";

        // validasi Logout
        if(request.getParameter("page") != null){
            if(request.getParameter("page").equals("logout")){
                request.getSession().removeAttribute("superadminid");
            }
        }

        request.getRequestDispatcher(address).forward(request, response);
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

        // Initial address
        String address = "/view/login/superadmin_login.jsp";

        try {

            System.out.println(request.getParameter("password").hashCode());
            // Inisialisasi akun
            Model.SuperAdmin superAdmin = superAdminService.getSuperAdmin(request.getParameter("username"));

            // Apakah akun tersedia?
            if(superAdmin != null){
                if(superAdmin.getPassword().equals(request.getParameter("password").hashCode() + "")) {

                    // Set session superAdminid dan superAdminname
                    request.getSession().setAttribute("superadminid", superAdmin.getId());

                    // Redirect halaman success
                    address = "/view/database/success.jsp";
                    request.setAttribute("title", "Login");
                    request.setAttribute("complete", "Sukses");
                    request.setAttribute("link", "/supermenu");

                } else{

                    // Redirect halaman success
                    address = "/view/database/success.jsp";
                    request.setAttribute("title", "Login");
                    request.setAttribute("complete", "Gagal");
                    request.setAttribute("link", "/super");
                }
            } else {
                // Redirect halaman success
                address = "/view/database/success.jsp";
                request.setAttribute("title", "Login");
                request.setAttribute("complete", "Gagal");
                request.setAttribute("link", "/super");
            }

            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
