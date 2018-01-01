package Controller.Studio;

import Model.Studio;
import Model.StudioType;
import Service.FilmService;
import Service.FilmServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Sebuah kelas yang menghandle list studio
 * url: /admin/studio
 */
@WebServlet("/admin/studio")
public class StudioMenu extends HttpServlet{
    FilmService studioService = new FilmServiceDatabase();

    /**
     * Sebuah method GET yang memberikan halaman list studio
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            // Pengambilan data list studio dan juga type studio untuk diretrieve pada menu
            List<Studio> studios = studioService.getAllStudio((int)request.getSession().getAttribute("storeid"), 0);
            List<StudioType> studioTypeList = studioService.getShowAllStudioType((int)request.getSession().getAttribute("storeid"));

            // Pengambilan data jumlah halaman yang akan ditampilkan pada menu
            int pageCounter = studioService.getCountAllStudio((int)request.getSession().getAttribute("storeid"));

            request.setAttribute("type", studioTypeList);
            request.setAttribute("studios", studios);
            request.setAttribute("page", pageCounter);

            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
