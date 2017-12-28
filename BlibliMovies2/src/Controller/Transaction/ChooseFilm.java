package Controller.Transaction;

import Model.Film;
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

@WebServlet("/cashier/film")
public class ChooseFilm extends HttpServlet {

    FilmService filmService = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/transaction/choose_film.jsp";

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
            List<Film> films = filmService.getAllFilm((int)request.getSession().getAttribute("storeid"));
            for(int i = 0; i < films.size(); i++){
                films.get(i).setScreeningTimes(filmService.getAllScreeningTime((int)request.getSession().getAttribute("storeid"), films.get(i).getId()+ ""));
                films.get(i).setCover("/uploads" + films.get(i).getCover());
            }
            request.setAttribute("films", films);
        } catch (SQLException e){
            System.out.print(e.getMessage());
        }

        request.getRequestDispatcher(address).forward(request, response);
    }
}
