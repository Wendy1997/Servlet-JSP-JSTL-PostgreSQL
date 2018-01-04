package Controller.Transaction;

import Model.Film;
import Model.FilmGenre;
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
import java.util.List;

/**
 * Sebuah kelas yang menlihat detail dari film yang telah dipilih pada choose film
 * url: /cashier/dilm/detail
 */
@WebServlet("/cashier/film/detail")
public class ChooseFilmDetail extends HttpServlet {
    FilmService filmService = new FilmServiceDatabase();
    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String chooseFilmDetailAddress = "/view/transaction/confirmation_film.jsp";
    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";

    /**
     * Sebuah method GET yang akan menampilkan data dari film yang telah dipilih
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

        try{
            // Pengambilan data film yang telah dipilih
            Film film = filmService.getFilmTrue(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession));
            film.setCover("/uploads" + film.getCover());
            FilmGenre filmGenre = filmService.getFilmGenre(film.getGenre() + "", (int)request.getSession().getAttribute(storeIdSession));

            // Pengambilan data screening time dan studio yang telah dipilih
            ScreeningTime screeningTime = filmService.getScreeningTimeTrue(request.getParameter("screeningtime"), request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession));
            Studio studio = filmService.getStudioTrue(screeningTime.getStudioId() + "", (int)request.getSession().getAttribute(storeIdSession));

            request.setAttribute("genre", filmGenre.getGenre());
            request.setAttribute("film", film);
            request.setAttribute("date", request.getParameter("date"));
            request.setAttribute("screeningTime", screeningTime);
            request.setAttribute("studio", studio);
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(chooseFilmDetailAddress).forward(request, response);
    }
}
