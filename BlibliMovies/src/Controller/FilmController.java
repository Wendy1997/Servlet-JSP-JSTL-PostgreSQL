package Controller;

import DAO.FilmDAO;
import Model.Film;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FilmController extends HttpServlet {
    FilmDAO filmDAO;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        filmDAO = new FilmDAO();
        String address = "film/film_menu.jsp";
        String addressNow = request.getServletPath();
        try{
            if(addressNow.equals("/admin/film/add")){
                address = "film_add.jsp";
            } else{
                List<Film> films = filmDAO.getAllFilm();
                request.setAttribute("films", films);
            }
            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        filmDAO = new FilmDAO();
        String address = request.getServletPath();
        try{

            if(request.getServletPath().equals("/admin/film/add")){
                Film film = new Film(0,
                        (String)request.getSession().getAttribute("storename"),
                        request.getParameter("cover"),
                        request.getParameter("nama"),
                        request.getParameter("genre"),
                        Integer.parseInt(request.getParameter("durasi")),
                        request.getParameter("director"),
                        Double.parseDouble(request.getParameter("rating")),
                        Integer.parseInt(request.getParameter("jumlah")),
                        request.getParameter("waktu_mulai"),
                        request.getParameter("waktu_akhir"),
                        request.getParameter("language"),
                        request.getParameter("subtitle"),
                        request.getParameter("actor"),
                        request.getParameter("sinopsis"));

                System.out.println(film.toString());
                filmDAO.addFilm(film);
                address = "film_success.jsp";
            }

            request.getRequestDispatcher(address).forward(request,response);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
