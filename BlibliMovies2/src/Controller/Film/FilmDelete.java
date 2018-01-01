package Controller.Film;

import DAO.FilmDAO;
import Model.Film;
import Service.FilmService;
import Service.FilmServiceDatabase;
import jdk.nashorn.internal.runtime.ECMAException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Sebuah kelas yang menghandle penghapusan ataupun pengembalian film
 * url: /admin/film/delete
 */
@WebServlet("/admin/film/delete")
public class FilmDelete extends HttpServlet {
    FilmService filmService = new FilmServiceDatabase();

    /**
     * Sebuah method GET yang akan melakukan penghapusan ataupun pengembalian film
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Initial Address
        String address = "/view/database/film/film_menu.jsp";

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
            // Pengambilan data film yang bersangkutan
            Film film = filmService.getFilm(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));

            // Pengecekan apakah status film tersebut aktif atau tidak
            if(film.isStatus()){

                // Jika aktif maka akan didelete
                filmService.deleteFilm(film.getId() + "", (int)request.getSession().getAttribute("storeid"));
                request.setAttribute("complete", "Deleted");
            } else{

                // Jika pasif maka akan di retrieve
                filmService.retrieveFilm(film.getId() + "", (int)request.getSession().getAttribute("storeid"));
                request.setAttribute("complete", "Retrieved");
            }

            // Redirect menuju halaman success
            address = "/view/database/success.jsp";
            request.setAttribute("title", "Film");
            request.setAttribute("link", "/admin/film");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
