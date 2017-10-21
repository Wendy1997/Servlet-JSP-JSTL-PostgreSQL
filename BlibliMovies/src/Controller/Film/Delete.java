package Controller.Film;

import DAO.FilmDAO;
import Model.Film;
import Service.FilmService;
import Service.FilmServiceDatabase;
import jdk.nashorn.internal.runtime.ECMAException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/film/delete")
public class Delete extends HttpServlet {
    FilmService filmService = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            Film film = filmService.getFilm(request.getParameter("id"));

            filmService.deleteFilm(film.getId() + "");

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Film");
            request.setAttribute("complete", "Deleted");
            request.setAttribute("link", "/admin/film");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
