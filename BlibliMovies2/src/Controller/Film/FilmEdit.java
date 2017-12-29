package Controller.Film;

import DAO.FilmDAO;
import Model.Film;
import Model.FilmGenre;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/admin/film/edit")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100)   	// 100 MB
public class FilmEdit extends HttpServlet{
    FilmService filmService = new FilmServiceDatabase();
    private static final String UPLOAD_DIR = "web\\uploads";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/film/film_edit.jsp";

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

        try {
            Film film = filmService.getFilm(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));
            request.setAttribute("film", film);

            List<FilmGenre> filmGenreList = filmService.getAllFilmGenreTrue((int)request.getSession().getAttribute("storeid"));
            request.setAttribute("genre", filmGenreList);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            LocalDateTime now = LocalDateTime.now();
            String random = dtf.format(now);

            String cover = filmService.getFilm(request.getParameter("id"), (int)request.getSession().getAttribute("storeid")).getCover();
            String[] randomList = cover.split("/");
            String randomNumber = randomList[3].substring(randomList[3].length()-19, randomList[3].length()-5);

            Film film = new Film();

            if(request.getPart("file").getSubmittedFileName().length() > 0){
                String[] pathList = getServletContext().getRealPath("").split("\\\\");
                String uploadFilePath = "";
                for(int i = 0; i < pathList.length - 3; i++){
                    uploadFilePath += pathList[i] + "\\";
                }
                uploadFilePath += UPLOAD_DIR + "\\" + (int)request.getSession().getAttribute("storeid") + "\\film";

                File fileLama = new File(uploadFilePath + "\\" + randomList[3]);
                fileLama.delete();

                // creates the save directory if it does not exists
                File fileSaveDir = new File(uploadFilePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }

                String fileName = null;

                Part part = request.getPart("file");
                fileName = getFileName(part);
                part.write(uploadFilePath + "\\" + request.getParameter("nama") + " (" + request.getParameter("waktu_mulai").substring(0,4) + ") [" + random + "].jpg");
                randomNumber = random;

                film = new Film(
                        Integer.parseInt(request.getParameter("id")),
                        (int)request.getSession().getAttribute("storeid"),
                        "/" + (int)request.getSession().getAttribute("storeid") + "/film/" + request.getParameter("nama") + " (" + request.getParameter("waktu_mulai").substring(0,4) + ") [" + randomNumber + "].jpg",
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
                        request.getParameter("sinopsis"));
            } else {

                film = new Film(
                        Integer.parseInt(request.getParameter("id")),
                        (int)request.getSession().getAttribute("storeid"),
                        filmService.getFilm(request.getParameter("id"), (int)request.getSession().getAttribute("storeid")).getCover(),
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
                        request.getParameter("sinopsis"));
            }

            filmService.updateFilm(film);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Film");
            request.setAttribute("complete", "Updated");
            request.setAttribute("link", "/admin/film");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}