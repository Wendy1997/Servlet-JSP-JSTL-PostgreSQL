package Controller.MemberCard;

import Model.MemberCard;
import Service.MemberCardService;
import Service.MemberCardServiceDatabase;
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
 * Sebuah kelas yang menghandle pagination list mebmer card
 * url: /admin/membercard/page
 */
@WebServlet("/admin/membercard/page")
public class MemberCardMenuPagination extends HttpServlet {
    MemberCardService membercardService = new MemberCardServiceDatabase();

    private final String storeIdSession = "storeid";

    /**
     * Sebuah method GET yang memberikan data list member card pada suatu halaman
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

            // Pengambilan list fnb pada offset halaman tersebut
            List<MemberCard> membercards = membercardService.getAllMemberCard((int)request.getSession().getAttribute(storeIdSession), page);

            // Inisialisasi dan mengubah objek menjadi JSON
            Gson gson = new Gson();
            String json = gson.toJson(membercards);

            // Pengiriman data menuju AJAX
            PrintWriter out = response.getWriter();
            out.print("{\"result\" : " + json + "}");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
