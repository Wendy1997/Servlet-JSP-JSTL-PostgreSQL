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

@WebServlet("/admin/superadmin/delete")
public class SuperAdminDelete extends HttpServlet {
    SuperAdminService superAdminService = new SuperAdminServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/superadmin/superadmin_menu.jsp";

        // Validasi apakah sudah login as super
        if(request.getSession().getAttribute("superadminid") == null){
            address = "/view/login/superadmin_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        try{
            SuperAdmin superAdmin = superAdminService.getSuperAdmin(request.getParameter("id"));

            if(superAdmin.getStatus()){
                superAdminService.deleteSuperAdmin(superAdmin.getId());
                request.setAttribute("complete", "Deleted");
            } else{
                request.setAttribute("complete", "Retrieved");
                superAdminService.retrieveSuperAdmin(superAdmin.getId());
            }

            address = "/view/database/success.jsp";
            request.setAttribute("title", "Super Admin");
            request.setAttribute("link", "/admin/superadmin");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
