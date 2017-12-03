package Controller.Film;

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
import java.util.*;

@WebServlet("/admin/film/detail")
public class FilmDetail extends HttpServlet{
    FilmService filmService = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String address = "/view/database/film/film_detail.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storename") == null){
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
            Film film = filmService.getFilm(request.getParameter("id"), (String)request.getSession().getAttribute("storename"));
            List<ScreeningTime> screeningTimeList = filmService.getAllScreeningTime((String)request.getSession().getAttribute("storename"), request.getParameter("id"));

            Map<Integer, List<ScreeningTime>> screeningList = new TreeMap<>();

            for(int i = 0; i < screeningTimeList.size(); i++){
                if(screeningList.containsKey(screeningTimeList.get(i).getStudioId())){
                    screeningList.get(screeningTimeList.get(i).getStudioId()).add(screeningTimeList.get(i));
                } else{
                    List<ScreeningTime> list = new ArrayList<>();
                    list.add(screeningTimeList.get(i));
                    screeningList.put(screeningTimeList.get(i).getStudioId(), list);
                }
            }

            film.setCover("/uploads" + film.getCover());

            request.setAttribute("film", film);
            request.setAttribute("screeningTime", screeningList);

            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
