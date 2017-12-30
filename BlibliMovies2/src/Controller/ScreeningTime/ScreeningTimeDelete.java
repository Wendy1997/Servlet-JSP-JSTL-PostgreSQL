package Controller.ScreeningTime;

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

@WebServlet("/admin/screentime/delete")
public class ScreeningTimeDelete extends HttpServlet{
    FilmService filmService = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storeid") == null){
            String address = "/view/login/store_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute("role") == null){
            String address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute("role").equals("admin")){
            String address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        try{
            ScreeningTime screeningTime = filmService.getScreeningTime(request.getParameter("id"), request.getParameter("filmid"), (int)request.getSession().getAttribute("storeid"));

            if(screeningTime.isStatus()){
                filmService.deleteScreeningTime(request.getParameter("id"), request.getParameter("filmid"), (int)request.getSession().getAttribute("storeid"));
                request.setAttribute("complete", "Deleted");
            } else {
                filmService.retrieveScreeningTime(request.getParameter("id"), request.getParameter("filmid"), (int)request.getSession().getAttribute("storeid"));
                request.setAttribute("complete", "Retrieved");
            }

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Screening Time");
            request.setAttribute("link", "/admin/screentime?filmid=" + request.getParameter("filmid"));

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
