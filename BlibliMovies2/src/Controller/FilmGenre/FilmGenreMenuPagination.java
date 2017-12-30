package Controller.FilmGenre;

import Model.Film;
import Model.FilmGenre;
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

@WebServlet("/admin/filmgenre/page")
public class FilmGenreMenuPagination extends HttpServlet {
    FilmService filmService = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            int page = (Integer.parseInt(request.getParameter("page")) - 1) * 10;
            List<FilmGenre> films = filmService.getAllFilmGenre((int)request.getSession().getAttribute("storeid"), page);

            Gson gson = new Gson();
            String json = gson.toJson(films);

            PrintWriter out = response.getWriter();
            out.print("{\"count\": " + 123 + ",");
            out.print(" \"result\" : " + json + "}");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
