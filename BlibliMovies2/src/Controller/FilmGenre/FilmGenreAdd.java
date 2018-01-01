package Controller.FilmGenre;

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
import java.sql.SQLException;
import java.util.List;

/**
 * Sebuah kelas yang menghandle penambahan film genre
 * url: /admin/filmgenre/add
 */
@WebServlet("/admin/filmgenre/add")
public class FilmGenreAdd extends HttpServlet {
    FilmService filmService = new FilmServiceDatabase();

    /**
     * Sebuah method GET yang memberikan form penambahan film genre
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Initial address
        String address = "/view/database/filmgenre/filmgenre_add.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storeid") == null){
            address = "/view/login/store_login.jsp";
        }

        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute("role") == null){
            address = "/view/login/account_login.jsp";
        }

        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute("role").equals("admin")){
            address = "/view/login/account_login.jsp";
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman tambah film genre
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            // Inisialisasi Film Genre
            FilmGenre filmGenre = new FilmGenre( request.getParameter("genre"),
                    (int)request.getSession().getAttribute("storeid"));

            // Sebuah method yang akan memasukkan film genre pada database
            filmService.addFilmGenre(filmGenre);

            // Redirect menuju halaman success
            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Film Genre");
            request.setAttribute("complete", "Created");
            request.setAttribute("link", "/admin/filmgenre");

            request.getRequestDispatcher(address).forward(request,response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
