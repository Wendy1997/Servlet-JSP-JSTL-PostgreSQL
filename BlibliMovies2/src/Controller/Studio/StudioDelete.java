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

    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String successAddress = "/view/database/success.jsp";

    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";

    private final String title = "Studio";
    private final String statusDeleteBerhasil = "Deleted";
    private final String statusRetrieveBerhasil = "Retrieved";
    private final String link = "/admin/studio";

    /**
     * Sebuah method GET yang akan melakukan penghapusan ataupun pengembalian studio
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
            // Pengambilan data studio yang bersangkutan
            Studio studio = studioService.getStudio(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession));

            // Pengecekan apakah status studio tersebut aktif atau tidak
            if(studio.isStatus()){

                // Jika aktif maka akan didelete
                studioService.deleteSrudio(studio.getId() + "", (int)request.getSession().getAttribute(storeIdSession));
                request.setAttribute("complete", statusDeleteBerhasil);
            } else {

                // Jika pasif maka akan di retrieve
                studioService.retrieveSrudio(studio.getId() + "", (int)request.getSession().getAttribute(storeIdSession));
                request.setAttribute("complete", statusRetrieveBerhasil);
            }

            // Redirect menuju halaman success
            request.setAttribute("title", title);
            request.setAttribute("link", link);

            request.getRequestDispatcher(successAddress).forward(request, response);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
