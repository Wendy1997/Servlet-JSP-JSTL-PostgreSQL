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

@WebServlet("/cashier/film/detail")
public class ChooseFilmDetail extends HttpServlet {

    FilmService filmService = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
            Film film = filmService.getFilm(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));
            film.setCover("/uploads" + film.getCover());

            ScreeningTime screeningTime = filmService.getScreeningTime(request.getParameter("screeningtime"), request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));
            Studio studio = filmService.getStudio(screeningTime.getStudioId() + "", (int)request.getSession().getAttribute("storeid"));

            request.setAttribute("film", film);
            request.setAttribute("screeningTime", screeningTime);
            request.setAttribute("studio", studio);
        } catch (SQLException e){
            System.out.print(e.getMessage());
        }

        request.getRequestDispatcher(address).forward(request, response);
    }
}
