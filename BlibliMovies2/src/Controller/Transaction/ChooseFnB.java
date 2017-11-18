package Controller.Transaction;

import Service.FnBService;
import Service.FnBServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebFault;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

@WebServlet("/cashier/fnb")
public class ChooseFnB extends HttpServlet{
    FnBService fnBService = new FnBServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/transaction/choose_fnb.jsp";

        try{
            request.setAttribute("fnblist", fnBService.getAllFnB("blibli"));
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }
}
