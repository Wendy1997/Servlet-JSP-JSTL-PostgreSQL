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

@WebServlet("/admin/superadmin/add")
public class SuperAdminAdd extends HttpServlet {
    SuperAdminService superAdminService = new SuperAdminServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/database/superadmin/superadmin_add.jsp";

        // Validasi apakah sudah login as super
        if(request.getSession().getAttribute("superadminid") == null){
            address = "/view/login/superadmin_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            String address = "/view/database/success.jsp";

            SuperAdmin superAdmin = new SuperAdmin( request.getParameter("username"),
                    request.getParameter("password").hashCode() + "");

            try{
                superAdminService.getSuperAdmin(request.getParameter("username")).getUsername();

                // Redirect menuju halaman success
                request.setAttribute("title", "Super Admin");
                request.setAttribute("complete", "Has Taken");
                request.setAttribute("link", "/admin/superadmin");

            } catch (NullPointerException e){
                superAdminService.addSuperAdmin(superAdmin);

                request.setAttribute("title", "Super Admin");
                request.setAttribute("complete", "Created");
                request.setAttribute("link", "/admin/superadmin");
            } finally {
                request.getRequestDispatcher(address).forward(request,response);
            }
        } catch (SQLException e){
           e.printStackTrace();
        }
    }
}
