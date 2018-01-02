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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/database/superadmin/superadmin_menu.jsp";

        // Validasi apakah sudah login as super
        if(request.getSession().getAttribute("superadminid") == null){
            address = "/view/login/superadmin_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        try{
            List<SuperAdmin> superAdmins = superAdminService.getAllSuperAdmin(0);
            int pageCounter = superAdminService.getCountAllSuperAdmin();

            request.setAttribute("superadmins", superAdmins);
            request.setAttribute("page", pageCounter);
            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
