package Controller.ScreeningTime;

import DAO.FilmDAO;
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

@WebServlet("/admin/screentime/add")
public class ScreeningTimeAdd extends HttpServlet{
    FilmService filmService = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/screening/screeningTime_add.jsp";

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

        request.setAttribute("filmid", request.getParameter("filmid"));
        request.setAttribute("duration", request.getParameter("duration"));

        try{
            request.setAttribute("studio", filmService.getAllStudio((String)request.getSession().getAttribute("storename")));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        request.getRequestDispatcher(address).forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            ScreeningTime screeningTime = new ScreeningTime(Integer.parseInt(request.getParameter("filmid")),
                    Integer.parseInt(request.getParameter("studio")),
                    (String) request.getSession().getAttribute("storename"),
                    request.getParameter("screen_time"),
                    Integer.parseInt(request.getParameter("duration")));

            filmService.addScreeningTime(screeningTime);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Screening Time");
            request.setAttribute("complete", "Added");
            request.setAttribute("link", "/admin/film/detail?id=" + request.getParameter("filmid"));

            request.getRequestDispatcher(address).forward(request,response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
