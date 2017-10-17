package Controller.Film;

import DAO.FilmDAO;
import Model.Film;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/film")
public class Menu extends HttpServlet{
    FilmDAO filmDAO;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        filmDAO = new FilmDAO();
        String address = "/view/database/film/film_menu.jsp";
        String addressNow = request.getServletPath();
        try{
            if(addressNow.equals("/admin/film/add")){
                address = "/view/database/film/film_add.jsp";
            } else{
                List<Film> films = filmDAO.getAllFilm();
                request.setAttribute("films", films);
            }
            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}