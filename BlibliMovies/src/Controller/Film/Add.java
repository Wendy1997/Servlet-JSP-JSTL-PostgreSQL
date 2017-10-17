package Controller.Film;

import DAO.FilmDAO;
import Model.Film;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/film/add")
public class Add extends HttpServlet {
    FilmDAO filmDAO;

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
                address = "/view/database/film/film_success.jsp";
            }

            request.getRequestDispatcher(address).forward(request,response);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
