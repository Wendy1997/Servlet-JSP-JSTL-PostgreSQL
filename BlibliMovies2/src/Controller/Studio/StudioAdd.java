package Controller.Studio;

import DAO.StudioDAO;
import Model.Studio;
import Service.FilmService;
import Service.FilmServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.zip.Adler32;

@WebServlet("/admin/studio/add")
public class StudioAdd extends HttpServlet{
    FilmService studioDAO = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/studio/studio_add.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storename") == null){
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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            Studio studio = new Studio((String)request.getSession().getAttribute("storeusername"),
                    request.getParameter("name"),
                    request.getParameter("type"),
                    Integer.parseInt(request.getParameter("price")));

            studioDAO.addStudio(studio);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Studio");
            request.setAttribute("complete", "Created");
            request.setAttribute("link", "/admin/studio");
            request.getRequestDispatcher(address).forward(request,response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
