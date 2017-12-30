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

@WebServlet("/admin/screentime")
public class ScreeningTimeMenu extends HttpServlet{
    FilmService filmService = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/database/screening/screeningTime_menu.jsp";

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

        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute("role").equals("admin")){
            address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        try{
            List<ScreeningTime> screeningTimeList = filmService.getAllScreeningTime((int)request.getSession().getAttribute("storeid"), request.getParameter("filmid"), 0);
            int pageCounter = filmService.getCountAllScreeningTime((int)request.getSession().getAttribute("storeid"), request.getParameter("filmid"));

            request.setAttribute("screenTime", screeningTimeList);
            request.setAttribute("film", filmService.getFilm(request.getParameter("filmid"), (int)request.getSession().getAttribute("storeid")));
            request.setAttribute("page", pageCounter);
            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
