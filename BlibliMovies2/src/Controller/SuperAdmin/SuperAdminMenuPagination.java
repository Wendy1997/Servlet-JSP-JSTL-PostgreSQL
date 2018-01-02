package Controller.SuperAdmin;

import Model.SuperAdmin;
import Service.SuperAdminService;
import Service.SuperAdminServiceDatabase;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/superadmin/page")
public class SuperAdminMenuPagination extends HttpServlet {
    SuperAdminService superAdminService = new SuperAdminServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            int page = (Integer.parseInt(request.getParameter("page")) - 1) * 10;
            List<SuperAdmin> superAdmins = superAdminService.getAllSuperAdmin(page);

            Gson gson = new Gson();
            String json = gson.toJson(superAdmins);

            PrintWriter out = response.getWriter();
            out.print("{\"count\": " + 123 + ",");
            out.print(" \"result\" : " + json + "}");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
