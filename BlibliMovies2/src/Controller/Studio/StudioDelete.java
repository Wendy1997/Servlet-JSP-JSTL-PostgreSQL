package Controller.Studio;

import Model.Studio;
import Service.FilmService;
import Service.FilmServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/studio/delete")
public class StudioDelete extends HttpServlet {
    FilmService studioService = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String address = "/view/database/studio/studio_menu.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storename") == null){
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
            Studio studio = studioService.getStudio(request.getParameter("id"), (String)request.getSession().getAttribute("storename"));

            studioService.deleteSrudio(studio.getId() + "", (String)request.getSession().getAttribute("storename"));

            address = "/view/database/success.jsp";
            request.setAttribute("title", "Studio");
            request.setAttribute("complete", "Deleted");
            request.setAttribute("link", "/admin/studio");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

