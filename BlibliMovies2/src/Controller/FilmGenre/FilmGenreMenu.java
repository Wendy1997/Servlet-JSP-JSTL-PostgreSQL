package Controller.Account;

import DAO.AccountDAO;
import Model.Account;
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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/filmgenre")
public class FilmGenreMenu extends HttpServlet{
    FilmService filmService = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/database/filmgenre/filmgenre_menu.jsp";

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
            List<FilmGenre> filmGenreList = filmService.getAllFilmGenre((String)request.getSession().getAttribute("storename"));
            request.setAttribute("genre", filmGenreList);
            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}