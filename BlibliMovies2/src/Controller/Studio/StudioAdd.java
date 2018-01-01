package Controller.Studio;

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
import java.util.List;

/**
 * Sebuah kelas yang menghandle penambahan studio
 * url: /admin/studio/add
 */
@WebServlet("/admin/studio/add")
public class StudioAdd extends HttpServlet {
    FilmService studioService = new FilmServiceDatabase();

    /**
     * Sebuah method GET yang memberikan form penambahan studio
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Initial Address
        String address = "/view/database/studio/studio_add.jsp";

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

        try{
            // Pengambilan data seluruh studio type untuk dimasukkan kedalam form
            List<StudioType> studioTypeList = studioService.getAllStudioTypeTrue((int)request.getSession().getAttribute("storeid"));
            request.setAttribute("type", studioTypeList);
        }catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman tambah studio
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            // Inisialisasi studio dari hasil form
            Studio studio = new Studio((int)request.getSession().getAttribute("storeid"),
                    request.getParameter("name"),
                    Integer.parseInt(request.getParameter("type")),
                    Integer.parseInt(request.getParameter("price")));

            // Sebuah method yang akan memasukkan studio ke dalam database
            studioService.addStudio(studio);

            // Redirect menuju halaman success
            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Studio");
            request.setAttribute("complete", "Created");
            request.setAttribute("link", "/admin/studio");

            request.getRequestDispatcher(address).forward(request,response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
