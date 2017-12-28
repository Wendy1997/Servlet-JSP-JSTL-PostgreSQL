package Controller.ScreeningTime;

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

@WebServlet("/admin/screentime/update")
public class ScreeningTimeUpdate extends HttpServlet {
    FilmService filmService = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/screening/screeningTime_edit.jsp";

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
            ScreeningTime screeningTime = filmService.getScreeningTime(request.getParameter("id"),
                    request.getParameter("filmid"),
                    (int)request.getSession().getAttribute("storeid"));

            request.setAttribute("filmid", request.getParameter("filmid"));
            request.setAttribute("duration", request.getParameter("duration"));
            request.setAttribute("screeningTime", screeningTime);
            request.setAttribute("studio", filmService.getAllStudio((int)request.getSession().getAttribute("storeid")));

            request.getRequestDispatcher(address).forward(request, response);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            ScreeningTime screeningTime = new ScreeningTime(Integer.parseInt(request.getParameter("id")),
                    Integer.parseInt(request.getParameter("filmid")),
                    Integer.parseInt(request.getParameter("studio")),
                    (int)request.getSession().getAttribute("storeid"),
                    request.getParameter("screen_time"),
                    Integer.parseInt(request.getParameter("duration")));

            filmService.updateScreeningTime(screeningTime);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Screening Time");
            request.setAttribute("complete", "Updated");
            request.setAttribute("link", "/admin/film/detail?id=" + request.getParameter("filmid"));

            request.getRequestDispatcher(address).forward(request,response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
