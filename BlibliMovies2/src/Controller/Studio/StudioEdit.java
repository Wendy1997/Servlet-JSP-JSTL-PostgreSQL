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
 * Sebuah kelas yang menghandle pengeditan studio
 * url: /admin/studio/edit
 */
@WebServlet("/admin/studio/edit")
public class StudioEdit extends HttpServlet{
    FilmService studioService = new FilmServiceDatabase();

    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String editStudioAddress = "/view/database/studio/studio_edit.jsp";
    private final String successAddress = "/view/database/success.jsp";

    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";

    private final String title = "Studio";
    private final String statusEditBerhasil = "Updated";
    private final String link = "/admin/studio";

    /**
     * Sebuah method GET yang memberikan halaman form edit studio
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

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


        try {
            // Pengambilan data studio yang bersangkutan
            Studio studio = studioService.getStudio(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession));
            request.setAttribute("studio", studio);

            // Pengambilan seluruh type yang akan ditampilkan pada form
            List<StudioType> studioTypeList = studioService.getAllStudioTypeTrue((int)request.getSession().getAttribute(storeIdSession));
            request.setAttribute("type", studioTypeList);
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(editStudioAddress).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman edit studio
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            // Inisialisasi Studio
            Studio studio = new Studio( Integer.parseInt(request.getParameter("id")),
                    (int)request.getSession().getAttribute(storeIdSession),
                    request.getParameter("name"),
                    Integer.parseInt(request.getParameter("type")),
                    Integer.parseInt(request.getParameter("price")));

            // Sebuah method yang akan memasukkan studio pada database
            studioService.updateStudio(studio);

            // Redirect menuju halaman success
            request.setAttribute("title", title);
            request.setAttribute("complete", statusEditBerhasil);
            request.setAttribute("link", link);

            request.getRequestDispatcher(successAddress).forward(request, response);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}