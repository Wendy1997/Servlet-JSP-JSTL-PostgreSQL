package Controller.StudioType;

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
import java.util.List;

/**
 * Sebuah kelas yang menghandle penambahan studio type
 * url: /admin/studiotype/add
 */
@WebServlet("/admin/studiotype/add")
public class StudioTypeAdd extends HttpServlet {
    FilmService filmService = new FilmServiceDatabase();

    /**
     * Sebuah method GET yang memberikan form penambahan studio type
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Initial Address
        String address = "/view/database/studiotype/studiotype_add.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storeid") == null){
            address = "/view/login/store_login.jsp";
        }

        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute("role") == null){
            address = "/view/login/account_login.jsp";
        }

        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute("role").equals("admin")){
            address = "/view/login/account_login.jsp";
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman tambah studio type
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            // Inisialisasi studio type dari hasil form
            StudioType studio = new StudioType( request.getParameter("type"),
                    (int)request.getSession().getAttribute("storeid"));

            // Sebuah method yang akan memasukkan studio type ke dalam database
            filmService.addStudioType(studio);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Studio Type");
            request.setAttribute("complete", "Created");
            request.setAttribute("link", "/admin/studiotype");

            request.getRequestDispatcher(address).forward(request,response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
