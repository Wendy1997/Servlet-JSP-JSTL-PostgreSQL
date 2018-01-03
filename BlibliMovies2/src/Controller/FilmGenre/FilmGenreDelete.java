package Controller.FilmGenre;

import DAO.AccountDAO;
import Model.Account;
import Model.FilmGenre;
import Service.AccountService;
import Service.AccountServiceDatabase;
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
 * Sebuah kelas yang menghandle penghapusan ataupun pengembalian film genre
 * url: /admin/filmgenre/delete
 */
@WebServlet("/admin/filmgenre/delete")
public class FilmGenreDelete extends HttpServlet {
    FilmService filmService = new FilmServiceDatabase();

    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String successAddress = "/view/database/success.jsp";

    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";

    private final String title = "Film Genre";
    private final String statusDeleteBerhasil = "Deleted";
    private final String statusRetrieveBerhasil = "Retrieved";
    private final String link = "/admin/filmgenre";

    /**
     * Sebuah method GET yang akan melakukan penghapusan ataupun pengembalian film genre
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


        try{
            // Pengambilan data film genre yang bersangkutan
            FilmGenre filmGenre = filmService.getFilmGenre(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession));

            // Pengecekan apakah status film genre tersebut aktif atau tidak
            if(filmGenre.isStatus()){

                // Jika aktif maka akan didelete
                filmService.deleteFilmGenre(filmGenre.getId() + "", (int)request.getSession().getAttribute(storeIdSession));
                request.setAttribute("complete", statusDeleteBerhasil);
            } else{

                // Jika pasif maka akan di retrieve
                filmService.retrieveFilmGenre(filmGenre.getId() + "", (int)request.getSession().getAttribute(storeIdSession));
                request.setAttribute("complete", statusRetrieveBerhasil);
            }

            // Redirect menuju halaman success
            request.setAttribute("title", title);
            request.setAttribute("link", link);

            request.getRequestDispatcher(successAddress).forward(request, response);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
