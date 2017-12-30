package Controller.FnBType;

import Model.FnBType;
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
import java.util.List;
import java.util.ServiceConfigurationError;

@WebServlet("/admin/fnbtype/page")
public class FnBTypeMenuPagination extends HttpServlet {
    FnBService fnbService = new FnBServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            int page = (Integer.parseInt(request.getParameter("page")) - 1) * 10;
            List<FnBType> fnbs = fnbService.getAllFnBType((int)request.getSession().getAttribute("storeid"), page);

            Gson gson = new Gson();
            String json = gson.toJson(fnbs);

            PrintWriter out = response.getWriter();
            out.print("{\"count\": " + 123 + ",");
            out.print(" \"result\" : " + json + "}");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
