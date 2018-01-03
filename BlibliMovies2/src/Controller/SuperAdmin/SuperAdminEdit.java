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

@WebServlet("/admin/superadmin/edit")
public class SuperAdminEdit extends HttpServlet{
    SuperAdminService superAdminService = new SuperAdminServiceDatabase();

    private final String superLoginAddress = "/view/login/superadmin_login.jsp";
    private final String successAddress = "/view/database/success.jsp";
    private final String editSuperAdminAddress = "/view/database/superadmin/superadmin_edit.jsp";

    private final String superAdminSession = "superadminid";

    private final String title = "Super Admin";
    private final String statusEditBerhasil = "Updated";
    private final String link = "/admin/superadmin";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Validasi apakah sudah login as super
        if(request.getSession().getAttribute(superAdminSession) == null){
            request.getRequestDispatcher(superLoginAddress).forward(request, response);
        }

        try {
            SuperAdmin superAdmin = superAdminService.getSuperAdmin(request.getParameter("id"));
            request.setAttribute("superadmin", superAdmin);

            superAdmin.toString();

        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(editSuperAdminAddress).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            if(request.getParameter("password").length() == 0){
                response.sendRedirect("/admin/superadmin");
            } else {
                SuperAdmin superAdmin = new SuperAdmin( request.getParameter("username"),
                        request.getParameter("password").hashCode() + "",
                        Integer.parseInt(request.getParameter("id")));

                superAdminService.updateSuperAdmin(superAdmin);

                // Redirect menuju halaman success
                request.setAttribute("title", title);
                request.setAttribute("complete", statusEditBerhasil);
                request.setAttribute("link", link);

                request.getRequestDispatcher(successAddress).forward(request, response);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}