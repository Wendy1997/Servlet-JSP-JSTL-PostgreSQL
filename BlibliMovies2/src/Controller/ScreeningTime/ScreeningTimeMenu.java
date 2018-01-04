package Controller.ScreeningTime;

import DAO.FilmDAO;
import Model.Film;
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
import java.util.List;

/**
 * Sebuah kelas yang menghandle list screening time
 * url: /admin/screentime
 */
@WebServlet("/admin/screentime")
public class ScreeningTimeMenu extends HttpServlet{
    FilmService filmService = new FilmServiceDatabase();
    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String menuScreeningTimeAddress = "/view/database/screening/screeningTime_menu.jsp";
    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";

    /**
     * Sebuah method GET yang memberikan halaman list screening time
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
            // Pengambilan data list screening time untuk diretrieve pada menu
            List<ScreeningTime> screeningTimeList = filmService.getAllScreeningTime((int)request.getSession().getAttribute(storeIdSession), request.getParameter("filmid"), 0);

            // Pengambilan data jumlah halaman yang akan ditampilkan pada menu
            int pageCounter = filmService.getCountAllScreeningTime((int)request.getSession().getAttribute(storeIdSession), request.getParameter("filmid"));

            request.setAttribute("screenTime", screeningTimeList);
            request.setAttribute("film", filmService.getFilm(request.getParameter("filmid"), (int)request.getSession().getAttribute(storeIdSession)));
            request.setAttribute("page", pageCounter);

            request.getRequestDispatcher(menuScreeningTimeAddress).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
