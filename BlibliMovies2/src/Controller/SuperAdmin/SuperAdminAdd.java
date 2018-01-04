package Controller.SuperAdmin;

import Model.*;
import Service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Sebuah method yang menghandle penambahan super admin
 *
 * url: /admin/superadmin/add
 */
@WebServlet("/admin/superadmin/add")
public class SuperAdminAdd extends HttpServlet {
    SuperAdminService superAdminService = new SuperAdminServiceDatabase();
    private final String superLoginAddress = "/view/login/superadmin_login.jsp";
    private final String successAddress = "/view/database/success.jsp";
    private final String addSuperAdminAddress = "/view/database/superadmin/superadmin_add.jsp";
    private final String superAdminSession = "superadminid";
    private final String title = "Super Admin";
    private final String statusAddBerhasil = "Created";
    private final String statusAddGagal = "Has Taken";
    private final String link = "/admin/superadmin";

    /**
     * Sebuah method GET yang memberikan form penambahan super admin
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Validasi apakah sudah login as super
        if(request.getSession().getAttribute(superAdminSession) == null){
            request.getRequestDispatcher(superLoginAddress).forward(request, response);
        }

        request.getRequestDispatcher(addSuperAdminAddress).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman tambah superadmin
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            // Inisialisasi super admin
            SuperAdmin superAdmin = new SuperAdmin( request.getParameter("username"),
                    request.getParameter("password").hashCode() + "");

            // Sebuah pengecekan apakah superadmin telah tersedia atau belum
            try{
                superAdminService.getSuperAdmin(request.getParameter("username")).getUsername();

                // Redirect menuju halaman success
                request.setAttribute("title", title);
                request.setAttribute("complete", statusAddGagal);
                request.setAttribute("link", link);

            } catch (NullPointerException e){
                // Penambahan super admin
                superAdminService.addSuperAdmin(superAdmin);

                // Redirect menuju halaman success
                request.setAttribute("title", title);
                request.setAttribute("complete", statusAddBerhasil);
                request.setAttribute("link", link);

            } finally {
                request.getRequestDispatcher(successAddress).forward(request,response);
            }
        } catch (SQLException e){
           e.printStackTrace();
        }
    }
}
