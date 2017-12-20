package Controller.Account;

import DAO.AccountDAO;
import Model.Account;
import Model.AccountRole;
import Model.FilmGenre;
import Service.AccountService;
import Service.AccountServiceDatabase;
import Service.FilmService;
import Service.FilmServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/filmgenre/edit")
public class FilmGenreEdit extends HttpServlet{
    FilmService filmService = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/filmgenre/filmgenre_edit.jsp";

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
            FilmGenre filmGenre = filmService.getFilmGenre(request.getParameter("id"), (String)request.getSession().getAttribute("storename"));
            request.setAttribute("genre", filmGenre);
        } catch (Exception e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            FilmGenre filmGenre = new FilmGenre( request.getParameter("id"),
                    request.getParameter("genre"),
                    (String)request.getSession().getAttribute("storename"));
            filmService.updateFilmGenre(filmGenre);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Film Genre");
            request.setAttribute("complete", "Updated");
            request.setAttribute("link", "/admin/filmgenre");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
           e.printStackTrace();
        }
    }
}