package Controller.Transaction;

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
import java.util.List;

/**
 * Sebuah kelas yang menlihat detail dari film yang telah dipilih pada choose film
 * url: /cashier/dilm/detail
 */
@WebServlet("/cashier/film/detail")
public class ChooseFilmDetail extends HttpServlet {
    FilmService filmService = new FilmServiceDatabase();

    /**
     * Sebuah method GET yang akan menampilkan data dari film yang telah dipilih
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Initial Address
        String address = "/view/transaction/confirmation_film.jsp";

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

        try{
            // Pengambilan data film yang telah dipilih
            Film film = filmService.getFilmTrue(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));
            film.setCover("/uploads" + film.getCover());

            // Pengambilan data screening time dan studio yang telah dipilih
            ScreeningTime screeningTime = filmService.getScreeningTimeTrue(request.getParameter("screeningtime"), request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));
            Studio studio = filmService.getStudioTrue(screeningTime.getStudioId() + "", (int)request.getSession().getAttribute("storeid"));

            request.setAttribute("film", film);
            request.setAttribute("screeningTime", screeningTime);
            request.setAttribute("studio", studio);
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }
}
