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

    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String menuStudioAddress = "/view/database/studio/studio_menu.jsp";
    private final String successAddress = "/view/database/success.jsp";

    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";

    private final String title = "Account";
    private final String statusDeleteBerhasil = "Deleted";
    private final String statusRetrieveBerhasil = "Retrieved";
    private final String link = "admin";

    private final int initialPage = 0;

    /**
     * Sebuah method GET yang memberikan halaman list studio
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute(storeIdSession) == null){
            request.getRequestDispatcher(storeLoginAddress).forward(request, response);
        }
        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute(roleAccountSession) == null){
            request.getRequestDispatcher(accountLoginAddress).forward(request, response);
        }
        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute(roleAccountSession).equals(roleAdmin)){
            request.getRequestDispatcher(accountLoginAddress).forward(request, response);
        }


        try{
            // Pengambilan data list studio dan juga type studio untuk diretrieve pada menu
            List<Studio> studios = studioService.getAllStudio((int)request.getSession().getAttribute(storeIdSession), initialPage);
            List<StudioType> studioTypeList = studioService.getShowAllStudioType((int)request.getSession().getAttribute(storeIdSession));

            // Pengambilan data jumlah halaman yang akan ditampilkan pada menu
            int pageCounter = studioService.getCountAllStudio((int)request.getSession().getAttribute(storeIdSession));

            request.setAttribute("type", studioTypeList);
            request.setAttribute("studios", studios);
            request.setAttribute("page", pageCounter);

            request.getRequestDispatcher(menuStudioAddress).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
