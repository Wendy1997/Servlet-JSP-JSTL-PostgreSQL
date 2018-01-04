package Controller.Film;

import DAO.FilmDAO;
import Model.Film;
import Model.FilmGenre;
import Model.ScreeningTime;
import Service.FilmService;
import Service.FilmServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Sebuah kelas yang menghandle melihat detail dari film
 * url: /admin/film/detail
 */
@WebServlet("/admin/film/detail")
public class FilmDetail extends HttpServlet{
    FilmService filmService = new FilmServiceDatabase();

    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String detailFilmAddress = "/view/database/film/film_detail.jsp";

    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";

    /**
     * Sebuah method GET yang akan memberikan informasi detail mengenai film tersebut
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

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

            // Inisialisasi Film, Genre Film tersebut dan List Screening Time dari film tersebut
            Film film = filmService.getFilm(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession));
            List<ScreeningTime> screeningTimeList = filmService.getAllScreeningTimeTrue((int)request.getSession().getAttribute(storeIdSession), request.getParameter("id"));
            FilmGenre filmGenre = filmService.getFilmGenre(film.getGenre() + "", (int)request.getSession().getAttribute(storeIdSession));

            // Membuat sebuah objek yang akan mengklasifikasi screening time berdasarkan studio
            Map<Integer, List<ScreeningTime>> screeningList = new TreeMap<>();
            for(int i = 0; i < screeningTimeList.size(); i++){
                if(screeningList.containsKey(screeningTimeList.get(i).getStudioId())){
                    screeningList.get(screeningTimeList.get(i).getStudioId()).add(screeningTimeList.get(i));
                } else{
                    List<ScreeningTime> list = new ArrayList<>();
                    list.add(screeningTimeList.get(i));
                    screeningList.put(screeningTimeList.get(i).getStudioId(), list);
                }
            }

            // Menambahkan direktori pada cover
            film.setCover("/uploads" + film.getCover());

            request.setAttribute("film", film);
            request.setAttribute("genre", filmGenre);
            request.setAttribute("screeningTime", screeningList);

            request.getRequestDispatcher(detailFilmAddress).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
