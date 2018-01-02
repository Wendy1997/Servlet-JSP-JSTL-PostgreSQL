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

@WebServlet("/admin/superadmin/edit")
public class SuperAdminEdit extends HttpServlet{
    SuperAdminService superAdminService = new SuperAdminServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/superadmin/superadmin_edit.jsp";

        // Validasi apakah sudah login as super
        if(request.getSession().getAttribute("superadminid") == null){
            address = "/view/login/superadmin_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        try {
            SuperAdmin superAdmin = superAdminService.getSuperAdmin(request.getParameter("id"));
            request.setAttribute("superadmin", superAdmin);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        request.getRequestDispatcher(address).forward(request, response);
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

                String address = "/view/database/success.jsp";
                request.setAttribute("title", "Super Admin");
                request.setAttribute("complete", "Updated");
                request.setAttribute("link", "/admin/superadmin");

                request.getRequestDispatcher(address).forward(request, response);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}