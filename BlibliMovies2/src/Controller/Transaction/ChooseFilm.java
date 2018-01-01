package Controller.Transaction;

import Model.Film;
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
 * Sebuah kelas yang menghandle transaksi film
 * url: /cashier/film
 */
@WebServlet("/cashier/film")
public class ChooseFilm extends HttpServlet {
    FilmService filmService = new FilmServiceDatabase();

    /**
     * Sebuah method GET yang akan menampilkan seluruh film beserta dengan sinopsisnya
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Initial Address
        String address = "/view/transaction/choose_film.jsp";

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

        try{
            // Mengambil semua data film yang memiliki status aktif
            List<Film> films = filmService.getAllFilmTrue((int)request.getSession().getAttribute("storeid"));

            // Melakukan looping pada setiap filmnya untuk memasukkan list screening time dan menambahkan direktori uploads
            for(int i = 0; i < films.size(); i++){
                films.get(i).setScreeningTimes(filmService.getAllScreeningTimeTrue((int)request.getSession().getAttribute("storeid"), films.get(i).getId()+ ""));
                films.get(i).setCover("/uploads" + films.get(i).getCover());
            }
            request.setAttribute("films", films);
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }
}
