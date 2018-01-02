package Controller.Transaction;

import Model.Film;
import Model.ScreeningTime;
import Model.Studio;
import Model.StudioType;
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
import java.rmi.server.ServerCloneException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@WebServlet("/view/film")
public class ChooseFilmDate extends HttpServlet {
    FilmService filmService = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            // Mengambil semua data film yang memiliki status aktif
            List<Film> films = filmService.getAllFilmTrue((int)request.getSession().getAttribute("storeid"), request.getParameter("now"));

            // Melakukan looping pada setiap filmnya untuk memasukkan list screening time dan menambahkan direktori uploads
            for(int i = 0; i < films.size(); i++){
                films.get(i).setCover("/uploads" + films.get(i).getCover());

                List<ScreeningTime> temp = filmService.getAllScreeningTimeTrue((int)request.getSession().getAttribute("storeid"), films.get(i).getId()+ "");

                if(temp.size() == 0){
                    films.remove(i);
                    i--;
                } else {
                    // Membuat sebuah objek yang akan mengklasifikasi screening time berdasarkan studio
                    Map<String, List<ScreeningTime>> screeningList = new TreeMap<>();
                    for(int j = 0; j < temp.size(); j++){
                        Studio tempStudio = filmService.getStudio(temp.get(j).getStudioId() + "",(int)request.getSession().getAttribute("storeid"));
                        StudioType tempStudioType = filmService.getStudioType(tempStudio.getType() + "", (int)request.getSession().getAttribute("storeid"));
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

            Gson gson = new Gson();
            String json = gson.toJson(films);

            PrintWriter out = response.getWriter();
            out.print(json);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
