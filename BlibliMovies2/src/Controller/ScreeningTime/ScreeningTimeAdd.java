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

/**
 * Sebuah kelas yang menghandle penambahan screening time
 * url: /admin/screentime/add
 */
@WebServlet("/admin/screentime/add")
public class ScreeningTimeAdd extends HttpServlet{
    FilmService filmService = new FilmServiceDatabase();

    /**
     * Sebuah method GET yang memberikan form penambahan screening time
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Initial Address
        String address = "/view/database/screening/screeningTime_add.jsp";

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

        request.setAttribute("filmid", request.getParameter("filmid"));
        request.setAttribute("duration", request.getParameter("duration"));

        try{
            request.setAttribute("studio", filmService.getAllStudioTrue((int)request.getSession().getAttribute("storeid")));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        request.getRequestDispatcher(address).forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            // Inisialisasi screening time
            ScreeningTime screeningTime = new ScreeningTime(Integer.parseInt(request.getParameter("filmid")),
                    Integer.parseInt(request.getParameter("studio")),
                    (int)request.getSession().getAttribute("storeid"),
                    request.getParameter("screen_time"),
                    Integer.parseInt(request.getParameter("duration")));

            // Sebuah method yang akan memasukkan screening time pada database
            filmService.addScreeningTime(screeningTime);

            // Redirect menuju halaman success
            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Screening Time");
            request.setAttribute("complete", "Added");
            request.setAttribute("link", "/admin/screentime?filmid=" + request.getParameter("filmid"));

            request.getRequestDispatcher(address).forward(request,response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
