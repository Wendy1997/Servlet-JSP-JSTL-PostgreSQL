package Controller.FnBSize;

import Model.FnBSize;
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

/**
 * Sebuah kelas yang menghandle pagination list fnb size
 * url: /admin/fnbsize/page
 */
@WebServlet("/admin/fnbsize/page")
public class FnBSizeMenuPagination extends HttpServlet {
    FnBService fnbService = new FnBServiceDatabase();

    private final String storeIdSession = "storeid";

    /**
     * Sebuah method GET yang memberikan data list fnb size pada suatu halaman
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            // Pengambilan data offset
            int page = (Integer.parseInt(request.getParameter("page")) - 1) * 10;

            // Pengambilan list fnb size pada offset halaman tersebut
            List<FnBSize> fnbs = fnbService.getAllFnBSize((int)request.getSession().getAttribute(storeIdSession), page);

            // Inisialisasi dan mengubah objek menjadi JSON
            Gson gson = new Gson();
            String json = gson.toJson(fnbs);

            // Pengiriman data menuju AJAX
            PrintWriter out = response.getWriter();
            out.print("{\"result\" : " + json + "}");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
