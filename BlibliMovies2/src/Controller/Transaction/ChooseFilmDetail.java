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

        try{
            Film film = filmService.getFilm(request.getParameter("id"), "blibli");

            ScreeningTime screeningTime = filmService.getScreeningTime(request.getParameter("screeningtime"), request.getParameter("id"), "blibli");
            Studio studio = filmService.getStudio(screeningTime.getStudioId() + "", "blibli");

            request.setAttribute("film", film);
            request.setAttribute("screeningTime", screeningTime);
            request.setAttribute("studio", studio);
        } catch (SQLException e){
            System.out.print(e.getMessage());
        }

        request.getRequestDispatcher(address).forward(request, response);
    }
}
