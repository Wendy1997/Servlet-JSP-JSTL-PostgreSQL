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

/**
 * Sebuah kelas yang menghandle pagination list super admin
 * url: /admin/superadmin/page
 */
@WebServlet("/admin/superadmin/page")
public class SuperAdminMenuPagination extends HttpServlet {
    SuperAdminService superAdminService = new SuperAdminServiceDatabase();

    /**
     * Sebuah method GET yang memberikan data list superadmin pada suatu halaman
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            // Pengambilan data offset
            int page = (Integer.parseInt(request.getParameter("page")) - 1) * 10;

            // Pengambilan list superadmin pada offset halaman tersebut
            List<SuperAdmin> superAdmins = superAdminService.getAllSuperAdmin(page);

            // Inisialisasi dan mengubah objek menjadi JSON
            Gson gson = new Gson();
            String json = gson.toJson(superAdmins);

            // Pengiriman data menuju AJAX
            PrintWriter out = response.getWriter();
            out.print("{\"result\" : " + json + "}");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
