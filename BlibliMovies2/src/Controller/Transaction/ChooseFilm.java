package Controller.Transaction;

import Model.Film;
import Model.ScreeningTime;
import Model.Studio;
import Model.StudioType;
import Service.FilmService;
import Service.FilmServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Sebuah kelas yang menghandle transaksi film
 * url: /cashier/film
 */
@WebServlet("/cashier/film")
public class ChooseFilm extends HttpServlet {
    FilmService filmService = new FilmServiceDatabase();
    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String chooseFilmAddress = "/view/transaction/choose_film.jsp";
    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";

    /**
     * Sebuah method GET yang akan menampilkan seluruh film beserta dengan sinopsisnya
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

        try{
            // Pengambilan data waktu saat ini
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();

            // Mengambil semua data film yang memiliki status aktif
            List<Film> films = filmService.getAllFilmTrue((int)request.getSession().getAttribute(storeIdSession), dtf.format(now));

            // Melakukan looping pada setiap filmnya untuk memasukkan list screening time dan menambahkan direktori uploads
            for(int i = 0; i < films.size(); i++){
                films.get(i).setCover("/uploads" + films.get(i).getCover());

                List<ScreeningTime> temp = filmService.getAllScreeningTimeTrue((int)request.getSession().getAttribute(storeIdSession), films.get(i).getId()+ "");

                if(temp.size() == 0){
                    films.remove(i);
                    i--;
                } else {
                    // Membuat sebuah objek yang akan mengklasifikasi screening time berdasarkan studio
                    Map<String, List<ScreeningTime>> screeningList = new TreeMap<>();
                    for(int j = 0; j < temp.size(); j++){
                        Studio tempStudio = filmService.getStudio(temp.get(j).getStudioId() + "",(int)request.getSession().getAttribute(storeIdSession));
                        StudioType tempStudioType = filmService.getStudioType(tempStudio.getType() + "", (int)request.getSession().getAttribute(storeIdSession));
                        if(screeningList.containsKey(tempStudioType.getType())){
                            screeningList.get(tempStudioType.getType()).add(temp.get(j));
                        } else{
                            List<ScreeningTime> list = new ArrayList<>();
                            list.add(temp.get(j));
                            screeningList.put(tempStudioType.getType(), list);
                        }
                    }

                    films.get(i).setScreeningList(screeningList);
                }
            }

            request.setAttribute("date", dtf.format(now));
            request.setAttribute("films", films);
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(chooseFilmAddress).forward(request, response);
    }
}
