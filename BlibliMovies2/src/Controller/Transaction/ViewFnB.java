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

/**
 * Sebuah kelas yang akan mengembalikan data fnb
 * url: /view/fnb
 */
@WebServlet("/view/fnb")
public class ViewFnB extends HttpServlet {

    FnBService fnBService = new FnBServiceDatabase();

    /**
     * Sebuah method POST yang akan mengirimkan data fnb ke AJAX
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Inisialisasi id fnb
        String id = request.getParameter("id");

        try {

            // Inisialisasi FnB
            FnB fnb = fnBService.getFnBTrue(id, (int)request.getSession().getAttribute("storeid"));

            // Merubah model menjadi json
            Gson gson = new Gson();
            String json = gson.toJson(fnb);

            // Mengirimkan kembali kepada AJAX as response
            PrintWriter out = response.getWriter();
            out.print(json);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
