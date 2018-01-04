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

/**
 * Sebuah kelas yang menghandle penghapusan ataupun pengembalian super admin
 * url: /admin/superadmin/delete
 */
@WebServlet("/admin/superadmin/delete")
public class SuperAdminDelete extends HttpServlet {
    SuperAdminService superAdminService = new SuperAdminServiceDatabase();
    private final String superLoginAddress = "/view/login/superadmin_login.jsp";
    private final String successAddress = "/view/database/success.jsp";
    private final String superAdminSession = "superadminid";
    private final String title = "Super Admin";
    private final String statusDeleteBerhasil = "Deleted";
    private final String statusRetrieveBerhasil = "Retrieved";
    private final String link = "/admin/superadmin";

    /**
     * Sebuah method GET yang akan melakukan penghapusan ataupun pengembalian super admin
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Validasi apakah sudah login as super
        if(request.getSession().getAttribute(superAdminSession) == null){
            request.getRequestDispatcher(superLoginAddress).forward(request, response);
        }

        try{
            // Inisialisasi store account
            SuperAdmin superAdmin = superAdminService.getSuperAdmin(request.getParameter("id"));

            // Pengecekan apakah dia aktif atau tidak
            if(superAdmin.getStatus()){

                // Jika aktif maka akan didelete
                superAdminService.deleteSuperAdmin(superAdmin.getId());
                request.setAttribute("complete", statusDeleteBerhasil);
            } else{

                // Jika pasif maka akan diretrieve
                request.setAttribute("complete", statusRetrieveBerhasil);
                superAdminService.retrieveSuperAdmin(superAdmin.getId());
            }

            // Redirect menuju halaman success
            request.setAttribute("title", title);
            request.setAttribute("link", link);

            request.getRequestDispatcher(successAddress).forward(request, response);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
