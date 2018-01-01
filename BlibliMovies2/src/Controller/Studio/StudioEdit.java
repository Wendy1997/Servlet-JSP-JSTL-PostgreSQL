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
 * Sebuah kelas yang menghandle pengeditan studio
 * url: /admin/studio/edit
 */
@WebServlet("/admin/studio/edit")
public class StudioEdit extends HttpServlet{
    FilmService studioService = new FilmServiceDatabase();

    /**
     * Sebuah method GET yang memberikan halaman form edit studio
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Initial Address
        String address = "/view/database/studio/studio_edit.jsp";

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
            // Pengambilan data studio yang bersangkutan
            Studio studio = studioService.getStudio(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));
            request.setAttribute("studio", studio);

            // Pengambilan seluruh type yang akan ditampilkan pada form
            List<StudioType> studioTypeList = studioService.getAllStudioTypeTrue((int)request.getSession().getAttribute("storeid"));
            request.setAttribute("type", studioTypeList);
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman edit studio
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            // Inisialisasi Studio
            Studio studio = new Studio( Integer.parseInt(request.getParameter("id")),
                    (int)request.getSession().getAttribute("storeid"),
                    request.getParameter("name"),
                    Integer.parseInt(request.getParameter("type")),
                    Integer.parseInt(request.getParameter("price")));

            // Sebuah method yang akan memasukkan studio pada database
            studioService.updateStudio(studio);

            // Redirect menuju halaman success
            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Studio");
            request.setAttribute("complete", "Updated");
            request.setAttribute("link", "/admin/studio");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}