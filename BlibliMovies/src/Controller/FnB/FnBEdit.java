package Controller.FnB;

import DAO.FnBDAO;
import Model.FnB;
import Service.FnBService;
import Service.FnBServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/fnb/edit")
public class FnBEdit extends HttpServlet{
    FnBService fnbService = new FnBServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/fnb/fnb_edit.jsp";

        try {
            FnB fnb = fnbService.getFnB(request.getParameter("id"));
            request.setAttribute("fnb", fnb);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            FnB fnb = new FnB(
                    Integer.parseInt(request.getParameter("id")),
                    (String)request.getSession().getAttribute("storename"),
                    request.getParameter("cover"),
                    request.getParameter("name"),
                    request.getParameter("type"),
                    request.getParameter("size"),
                    Integer.parseInt(request.getParameter("price")));

            fnbService.updateFnB(fnb);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Food and Beverages");
            request.setAttribute("complete", "Updated");
            request.setAttribute("link", "/admin/fnb");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}