package Controller.Film;

import DAO.FilmDAO;
import Model.Film;
import Model.FnB;
import Service.FilmService;
import Service.FilmServiceDatabase;
import Service.FnBService;
import Service.FnBServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/fnb")
public class FnBMenu extends HttpServlet{
    FnBService fnbService = new FnBServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/database/fnb/fnb_menu.jsp";

        try{
            List<FnB> fnbs = fnbService.getAllFnB();
            request.setAttribute("fnbs", fnbs);

            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
