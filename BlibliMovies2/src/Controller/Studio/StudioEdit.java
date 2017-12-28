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
import java.util.List;

@WebServlet("/admin/studio/edit")
public class StudioEdit extends HttpServlet{
    FilmService studioService = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
            Studio studio = studioService.getStudio(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));
            request.setAttribute("studio", studio);

            List<StudioType> studioTypeList = studioService.getAllStudioType((int)request.getSession().getAttribute("storeid"));
            request.setAttribute("type", studioTypeList);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            Studio studio = new Studio( Integer.parseInt(request.getParameter("id")),
                    (int)request.getSession().getAttribute("storeid"),
                    request.getParameter("name"),
                    request.getParameter("type"),
                    Integer.parseInt(request.getParameter("price")));

            studioService.updateStudio(studio);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Studio");
            request.setAttribute("complete", "Updated");
            request.setAttribute("link", "/admin/studio");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}