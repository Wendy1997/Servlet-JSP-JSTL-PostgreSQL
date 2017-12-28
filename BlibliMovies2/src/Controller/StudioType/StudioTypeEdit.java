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

@WebServlet("/admin/studiotype/edit")
public class StudioTypeEdit extends HttpServlet{
    FilmService studioService = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/studiotype/studiotype_edit.jsp";

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
            StudioType studioType = studioService.getStudioType(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));
            request.setAttribute("type", studioType);
        } catch (Exception e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            StudioType studioType = new StudioType( request.getParameter("id"),
                    request.getParameter("type"),
                    (int)request.getSession().getAttribute("storeid"));
            studioService.updateStudioType(studioType);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Studio Type");
            request.setAttribute("complete", "Updated");
            request.setAttribute("link", "/admin/studiotype");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
           e.printStackTrace();
        }
    }
}