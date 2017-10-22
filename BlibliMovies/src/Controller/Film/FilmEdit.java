package Controller.Film;

import DAO.FilmDAO;
import Model.Film;
import Service.FilmService;
import Service.FilmServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/film/edit")
public class FilmEdit extends HttpServlet{
    FilmService filmService = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/film/film_edit.jsp";

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

        try {
            Film film = filmService.getFilm(request.getParameter("id"), (String)request.getSession().getAttribute("storename"));
            request.setAttribute("film", film);
            request.setAttribute("studio", filmService.getAllStudio((String)request.getSession().getAttribute("storename")));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            Film film = new Film(
                    Integer.parseInt(request.getParameter("id")),
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

            filmService.updateFilm(film);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Film");
            request.setAttribute("complete", "Updated");
            request.setAttribute("link", "/admin/film");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}