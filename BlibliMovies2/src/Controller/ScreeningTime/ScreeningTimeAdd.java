package Controller.ScreeningTime;

import DAO.FilmDAO;
import Model.Film;
import Model.ScreeningTime;
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
 * Sebuah kelas yang menghandle penambahan screening time
 * url: /admin/screentime/add
 */
@WebServlet("/admin/screentime/add")
public class ScreeningTimeAdd extends HttpServlet{
    FilmService filmService = new FilmServiceDatabase();

    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String addScreeningTimeAddress = "/view/database/screening/screeningTime_add.jsp";
    private final String successAddress = "/view/database/success.jsp";

    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";

    private final String title = "Screening Time";
    private final String statusAddBerhasil = "Created";
    private final String link = "/admin/screentime";

    /**
     * Sebuah method GET yang memberikan form penambahan screening time
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


        request.setAttribute("filmid", request.getParameter("filmid"));
        request.setAttribute("duration", request.getParameter("duration"));

        try{
            request.setAttribute("studio", filmService.getAllStudioTrue((int)request.getSession().getAttribute(storeIdSession)));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        request.getRequestDispatcher(addScreeningTimeAddress).forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            // Inisialisasi screening time
            ScreeningTime screeningTime = new ScreeningTime(Integer.parseInt(request.getParameter("filmid")),
                    Integer.parseInt(request.getParameter("studio")),
                    (int)request.getSession().getAttribute(storeIdSession),
                    request.getParameter("screen_time"),
                    Integer.parseInt(request.getParameter("duration")));

            // Sebuah method yang akan memasukkan screening time pada database
            filmService.addScreeningTime(screeningTime);

            // Redirect menuju halaman success
            request.setAttribute("title", title);
            request.setAttribute("complete", statusAddBerhasil);
            request.setAttribute("link", link);

            request.getRequestDispatcher(successAddress).forward(request,response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
