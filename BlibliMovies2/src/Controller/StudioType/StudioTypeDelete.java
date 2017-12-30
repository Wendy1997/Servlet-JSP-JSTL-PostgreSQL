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

@WebServlet("/admin/studiotype/delete")
public class StudioTypeDelete extends HttpServlet {
    FilmService filmService = new FilmServiceDatabase();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/studiotype/studiotype_menu.jsp";

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
            StudioType studioType = filmService.getStudioType(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));

            if(studioType.isStatus()){
                filmService.deleteStudioType(studioType.getId() + "", (int)request.getSession().getAttribute("storeid"));
                request.setAttribute("complete", "Deleted");
            } else {
                filmService.retrieveStudioType(studioType.getId() + "", (int)request.getSession().getAttribute("storeid"));
                request.setAttribute("complete", "Retrieved");
            }

            address = "/view/database/success.jsp";
            request.setAttribute("title", "Studio Type");
            request.setAttribute("link", "/admin/studiotype");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
