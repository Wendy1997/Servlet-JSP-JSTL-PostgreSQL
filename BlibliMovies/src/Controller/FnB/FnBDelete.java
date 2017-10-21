package Controller.FnB;

import DAO.FnBDAO;
import Model.FnB;
import Service.FnBService;
import Service.FnBServiceDatabase;
import jdk.nashorn.internal.runtime.ECMAException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/fnb/delete")
public class FnBDelete extends HttpServlet {
    FnBService fnbService = new FnBServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            FnB fnb = fnbService.getFnB(request.getParameter("id"));

            fnbService.deleteFnB(fnb.getId() + "");

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "FnB");
            request.setAttribute("complete", "Deleted");
            request.setAttribute("link", "/admin/fnb");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
