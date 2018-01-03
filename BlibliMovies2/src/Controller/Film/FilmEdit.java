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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Sebuah kelas yang menghandle pengeditan film
 * url: /admin/film/edit
 */
@WebServlet("/admin/film/edit")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100)   	// 100 MB
public class FilmEdit extends HttpServlet{
    FilmService filmService = new FilmServiceDatabase();
    private static final String UPLOAD_DIR = "web\\uploads";

    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String editFilmAddress = "/view/database/film/film_edit.jsp";
    private final String successAddress = "/view/database/success.jsp";

    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";

    private final String title = "Film";
    private final String statusEditBerhasil = "Updated";
    private final String link = "/admin/film";

    /**
     * Sebuah method GET yang memberikan halaman form edit film
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

        try {

            // Pengambilan data film yang bersangkutan
            Film film = filmService.getFilm(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession));
            request.setAttribute("film", film);

            // Pengambilan seluruh genre yang akan ditampilkan pada form
            List<FilmGenre> filmGenreList = filmService.getAllFilmGenreTrue((int)request.getSession().getAttribute(storeIdSession));
            request.setAttribute("genre", filmGenreList);
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(editFilmAddress).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman edit film
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{

            // Pengambilan data waktu saat ini
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            LocalDateTime now = LocalDateTime.now();
            String random = dtf.format(now);

            // Pengambilan data cover sebelumnya
            String cover = filmService.getFilm(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession)).getCover();
            String[] randomList = cover.split("/");

            Film film = new Film();

            // Pengecekan apakah cover diupdate atau tidak
            if(request.getPart("file").getSubmittedFileName().length() > 0){

                // Pengambilan data lokasi source project
                String[] pathList = getServletContext().getRealPath("").split("\\\\");
                String uploadFilePath = "";
                for(int i = 0; i < pathList.length - 3; i++){
                    uploadFilePath += pathList[i] + "\\";
                }

                // Memberikan direktori upload file
                uploadFilePath += UPLOAD_DIR + "\\" + (int)request.getSession().getAttribute(storeIdSession) + "\\film";

                // Menghapus file lama
                File fileLama = new File(uploadFilePath + "\\" + randomList[3]);
                fileLama.delete();

                // Pengecekan apakah telah ada direktorinya. Jika belum maka akan dibuat
                File fileSaveDir = new File(uploadFilePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }

                // Save data ke direktori tersebut
                Part part = request.getPart("file");
                part.write(uploadFilePath + "\\" + request.getParameter("nama") + " (" + request.getParameter("waktu_mulai").substring(0,4) + ") [" + random + "].jpg");
                random = random;

                // Inisialisasi Film
                film = new Film(
                        Integer.parseInt(request.getParameter("id")),
                        (int)request.getSession().getAttribute(storeIdSession),
                        "/" + (int)request.getSession().getAttribute(storeIdSession) + "/film/" + request.getParameter("nama") + " (" + request.getParameter("waktu_mulai").substring(0,4) + ") [" + random + "].jpg",
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

                // Inisialisasi Film
                film = new Film(
                        Integer.parseInt(request.getParameter("id")),
                        (int)request.getSession().getAttribute(storeIdSession),
                        filmService.getFilm(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession)).getCover(),
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

            // Sebuah method yang akan memasukkan film pada database
            filmService.updateFilm(film);

            // Redirect menuju halaman success
            request.setAttribute("title", title);
            request.setAttribute("complete", statusEditBerhasil);
            request.setAttribute("link", link);

            request.getRequestDispatcher(successAddress).forward(request, response);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}