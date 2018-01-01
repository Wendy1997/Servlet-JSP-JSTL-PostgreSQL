package Controller.FilmGenre;
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

/**
 * Sebuah kelas yang menghandle list film genre
 * url: /admin/filmgenre
 */
@WebServlet("/admin/filmgenre")
public class FilmGenreMenu extends HttpServlet{
    FilmService filmService = new FilmServiceDatabase();

    /**
     * Sebuah method GET yang memberikan halaman list film genre
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Initial Address
        String address = "/view/database/filmgenre/filmgenre_menu.jsp";

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

            // Pengambilan data list film dan juga genre film untuk diretrieve pada menu
            List<FilmGenre> filmGenreList = filmService.getAllFilmGenre((int)request.getSession().getAttribute("storeid"), 0);

            // Pengambilan data jumlah halaman yang akan ditampilkan pada menu
            int pageCounter = filmService.getCountAllFilmGenre((int)request.getSession().getAttribute("storeid"));

            request.setAttribute("genre", filmGenreList);
            request.setAttribute("page", pageCounter);

            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
