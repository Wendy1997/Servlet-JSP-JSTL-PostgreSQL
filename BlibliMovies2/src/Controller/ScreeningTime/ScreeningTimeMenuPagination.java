package Controller.ScreeningTime;

import Model.ScreeningTime;
import Service.FilmService;
import Service.FilmServiceDatabase;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.ServiceConfigurationError;

/**
 * Sebuah kelas yang menghandle pagination list screening time
 * url: /admin/screentime/page
 */
@WebServlet("/admin/screentime/page")
public class ScreeningTimeMenuPagination extends HttpServlet {
    FilmService filmService = new FilmServiceDatabase();
    private final String storeIdSession = "storeid";

    /**
     * Sebuah method GET yang memberikan data list screening time pada suatu halaman
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            // Pengambilan data offset
            int page = (Integer.parseInt(request.getParameter("page")) - 1) * 10;

            // Pengambilan list screening time pada offset halaman tersebut
            List<ScreeningTime> screeningTimeList = filmService.getAllScreeningTime((int)request.getSession().getAttribute(storeIdSession), request.getParameter("filmid"), page);

            // Inisialisasi dan mengubah objek menjadi JSON
            Gson gson = new Gson();
            String json = gson.toJson(screeningTimeList);

            // Pengiriman data menuju AJAX
            PrintWriter out = response.getWriter();
            out.print("{\"result\" : " + json + "}");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
