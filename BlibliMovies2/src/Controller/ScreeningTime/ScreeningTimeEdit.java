package Controller.ScreeningTime;

import Model.ScreeningTime;
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
 * Sebuah kelas yang menghandle pengeditan screening time
 * url: /admin/screentime/edit
 */
@WebServlet("/admin/screentime/update")
public class ScreeningTimeEdit extends HttpServlet {
    FilmService filmService = new FilmServiceDatabase();

    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String editScreeningTimeAddress = "/view/database/screening/screeningTime_edit.jsp";
    private final String successAddress = "/view/database/success.jsp";

    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";

    private final String title = "Screening Time";
    private final String statusEditBerhasil = "Updated";
    private final String link = "/admin/screentime?filmid=";

    /**
     * Sebuah method GET yang memberikan halaman form edit screentime
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


        try{
            // Pengambilan data screening time yang bersangkutan
            ScreeningTime screeningTime = filmService.getScreeningTime(request.getParameter("id"),
                    request.getParameter("filmid"),
                    (int)request.getSession().getAttribute(storeIdSession));

            request.setAttribute("filmid", request.getParameter("filmid"));
            request.setAttribute("duration", request.getParameter("duration"));
            request.setAttribute("screeningTime", screeningTime);
            request.setAttribute("studio", filmService.getAllStudioTrue((int)request.getSession().getAttribute(storeIdSession)));

            screeningTime.toString();

            request.getRequestDispatcher(editScreeningTimeAddress).forward(request, response);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman edit screening time
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            // Inisialisasi Screening time
            ScreeningTime screeningTime = new ScreeningTime(Integer.parseInt(request.getParameter("id")),
                    Integer.parseInt(request.getParameter("filmid")),
                    Integer.parseInt(request.getParameter("studio")),
                    (int)request.getSession().getAttribute(storeIdSession),
                    request.getParameter("screen_time"),
                    Integer.parseInt(request.getParameter("duration")));

            // Sebuah method yang akan memasukkan screening time pada database
            filmService.updateScreeningTime(screeningTime);

            // Redirect menuju halaman success
            request.setAttribute("title", title);
            request.setAttribute("complete", statusEditBerhasil);
            request.setAttribute("link", link + request.getParameter("id"));

            request.getRequestDispatcher(successAddress).forward(request,response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
