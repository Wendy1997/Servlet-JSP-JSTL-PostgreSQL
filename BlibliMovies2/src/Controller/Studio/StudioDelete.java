package Controller.Studio;

import Model.Studio;
import Service.FilmService;
import Service.FilmServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Sebuah kelas yang menghandle penghapusan ataupun pengembalian studio
 * url: /admin/studio/delete
 */
@WebServlet("/admin/studio/delete")
public class StudioDelete extends HttpServlet {
    FilmService studioService = new FilmServiceDatabase();

    /**
     * Sebuah method GET yang akan melakukan penghapusan ataupun pengembalian studio
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Initial Address
        String address = "/view/database/studio/studio_menu.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storeid") == null){
            address = "/view/login/store_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute("role") == null){
            address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute("role").equals("admin")){
            address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        try{
            // Pengambilan data studio yang bersangkutan
            Studio studio = studioService.getStudio(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));

            // Pengecekan apakah status studio tersebut aktif atau tidak
            if(studio.isStatus()){

                // Jika aktif maka akan didelete
                studioService.deleteSrudio(studio.getId() + "", (int)request.getSession().getAttribute("storeid"));
                request.setAttribute("complete", "Deleted");
            } else {

                // Jika pasif maka akan di retrieve
                studioService.retrieveSrudio(studio.getId() + "", (int)request.getSession().getAttribute("storeid"));
                request.setAttribute("complete", "Retrieved");
            }

            // Redirect menuju halaman success
            address = "/view/database/success.jsp";
            request.setAttribute("title", "Studio");
            request.setAttribute("link", "/admin/studio");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
