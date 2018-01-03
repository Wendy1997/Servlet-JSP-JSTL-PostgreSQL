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

@WebServlet("/admin/superadmin")
public class SuperAdminMenu extends HttpServlet{
    SuperAdminService superAdminService = new SuperAdminServiceDatabase();

    private final String superLoginAddress = "/view/login/superadmin_login.jsp";
    private final String successAddress = "/view/database/success.jsp";
    private final String menuSuperAdminAddress = "/view/database/superadmin/superadmin_menu.jsp";

    private final String superAdminSession = "superadminid";

    private final String title = "Account";
    private final String statusDeleteBerhasil = "Deleted";
    private final String statusRetrieveBerhasil = "Retrieved";
    private final String link = "admin";

    private final int initialPage = 0;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Validasi apakah sudah login as super
        if(request.getSession().getAttribute(superAdminSession) == null){
            request.getRequestDispatcher(superLoginAddress).forward(request, response);
        }

        try{
            List<SuperAdmin> superAdmins = superAdminService.getAllSuperAdmin(initialPage);
            int pageCounter = superAdminService.getCountAllSuperAdmin();

            request.setAttribute("superadmins", superAdmins);
            request.setAttribute("page", pageCounter);
            request.getRequestDispatcher(menuSuperAdminAddress).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
