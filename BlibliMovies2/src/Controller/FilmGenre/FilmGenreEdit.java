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
 * Sebuah kelas yang menghandle pengeditan film genre
 * url: /admin/filmgenre/edit
 */
@WebServlet("/admin/filmgenre/edit")
public class FilmGenreEdit extends HttpServlet{
    FilmService filmService = new FilmServiceDatabase();
    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String editFilmGenreAddress = "/view/database/filmgenre/filmgenre_edit.jsp";
    private final String successAddress = "/view/database/success.jsp";
    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";
    private final String title = "Film Genre";
    private final String statusEditBerhasil = "Updated";
    private final String link = "/admin/filmgenre";

    /**
     * Sebuah method GET yang memberikan halaman form edit film genre
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute(storeIdSession) == null){
            request.getRequestDispatcher(storeLoginAddress).forward(request, response);
        }
        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute(roleAccountSession) == null){
            request.getRequestDispatcher(accountLoginAddress).forward(request, response);
        }
        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute(roleAccountSession).equals(roleAdmin)){
            request.getRequestDispatcher(accountLoginAddress).forward(request, response);
        }


        try {

            // Pengambilan data film genre yang bersangkutan
            FilmGenre filmGenre = filmService.getFilmGenre(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession));
            request.setAttribute("genre", filmGenre);

            // Validasi jika id tersedia atau tidak
            filmGenre.toString();

        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(editFilmGenreAddress).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman edit film
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            // Inisialisasi Film Genre
            FilmGenre filmGenre = new FilmGenre( Integer.parseInt(request.getParameter("id")),
                    request.getParameter("genre"),
                    (int)request.getSession().getAttribute(storeIdSession));

            // Sebuah method yang akan memasukkan film pada database
            filmService.updateFilmGenre(filmGenre);

            // Redirect menuju halaman success
            request.setAttribute("title", title);
            request.setAttribute("complete", statusEditBerhasil);
            request.setAttribute("link", link);

            request.getRequestDispatcher(successAddress).forward(request, response);

        } catch (Exception e){
           e.printStackTrace();
        }
    }
}