package Controller.Film;

import DAO.FilmDAO;
import Model.Film;
import Model.FilmGenre;
import Model.ScreeningTime;
import Model.Studio;
import Service.FilmService;
import Service.FilmServiceDatabase;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/admin/film/add")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100)   	// 100 MB
public class FilmAdd extends HttpServlet {
    FilmService filmService = new FilmServiceDatabase();
    private static final String UPLOAD_DIR = "web\\uploads";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/database/film/film_add.jsp";

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
            List<FilmGenre> filmGenreList = filmService.getAllFilmGenreTrue((int)request.getSession().getAttribute("storeid"));
            request.setAttribute("genre", filmGenreList);
        }catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String dateNow = dtf.format(now);

        String[] pathList = getServletContext().getRealPath("").split("\\\\");
        String uploadFilePath = "";
        for(int i = 0; i < pathList.length - 3; i++){
            uploadFilePath += pathList[i] + "\\";
        }
        uploadFilePath += UPLOAD_DIR + "\\" + (int)request.getSession().getAttribute("storeid") + "\\film";

        // creates the save directory if it does not exists
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        String fileName = null;

        Part part = request.getPart("file");
        fileName = getFileName(part);
        part.write(uploadFilePath + "\\" + request.getParameter("nama") + " (" + request.getParameter("waktu_mulai").substring(0,4) + ") [" + dateNow + "].jpg");

        try{
            Film film = new Film(
                    (int)request.getSession().getAttribute("storeid"),
                    "/" + (int)request.getSession().getAttribute("storeid") + "/film/" + request.getParameter("nama") + " (" + request.getParameter("waktu_mulai").substring(0,4) + ") [" + dateNow + "].jpg",
                request.getParameter("nama"),
                Integer.parseInt(request.getParameter("genre")),
                Integer.parseInt(request.getParameter("durasi")),
                request.getParameter("director"),
                Double.parseDouble(request.getParameter("rating")),
                Integer.parseInt(request.getParameter("jumlah")),
                request.getParameter("waktu_mulai"),
                request.getParameter("waktu_akhir"),
                request.getParameter("language"),
                request.getParameter("subtitle"),
                request.getParameter("actor"),
                request.getParameter("sinopsis")
            );

            filmService.addFilm(film);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Film");
            request.setAttribute("complete", "Created");
            request.setAttribute("link", "/admin/film");

            request.getRequestDispatcher(address).forward(request,response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}
