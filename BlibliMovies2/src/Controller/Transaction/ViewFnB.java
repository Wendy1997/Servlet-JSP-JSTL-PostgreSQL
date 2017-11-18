package Controller.Transaction;

import Model.FnB;
import Service.FnBService;
import Service.FnBServiceDatabase;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/view/fnb")
public class ViewFnB extends HttpServlet {

    FnBService fnBService = new FnBServiceDatabase();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");

        try {
            FnB fnb = fnBService.getFnB(id, "blibli");
            Gson gson = new Gson();

            String json = gson.toJson(fnb);

            PrintWriter out = response.getWriter();
            out.print(json);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
