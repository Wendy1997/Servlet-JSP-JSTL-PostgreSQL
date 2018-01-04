package Controller.SuperAdmin;

import Model.SuperAdmin;
import Service.SuperAdminService;
import Service.SuperAdminServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Sebuah kelas yang menghandle list superadmin
 * url: /admin/superadmin
 */
@WebServlet("/admin/superadmin")
public class SuperAdminMenu extends HttpServlet{
    SuperAdminService superAdminService = new SuperAdminServiceDatabase();
    private final String superLoginAddress = "/view/login/superadmin_login.jsp";
    private final String menuSuperAdminAddress = "/view/database/superadmin/superadmin_menu.jsp";
    private final String superAdminSession = "superadminid";
    private final int initialPage = 0;

    /**
     * Sebuah method GET yang memberikan halaman list super admin
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

        try{
            // Pengambilan seluruh superadmin
            List<SuperAdmin> superAdmins = superAdminService.getAllSuperAdmin(initialPage);

            // Pengambilan jumlah halaman
            int pageCounter = superAdminService.getCountAllSuperAdmin();

            request.setAttribute("superadmins", superAdmins);
            request.setAttribute("page", pageCounter);
            request.getRequestDispatcher(menuSuperAdminAddress).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
