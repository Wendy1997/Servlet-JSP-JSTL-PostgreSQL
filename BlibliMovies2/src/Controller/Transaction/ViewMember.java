package Controller.Transaction;

import Model.MemberCard;
import Model.OrderDetail;
import Model.Promo;
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

/**
 * Sebuah kelas yang mengecek apakah member tersedia atau tidak
 * url: /cashier/member
 */
@WebServlet("/cashier/member")
public class ViewMember extends HttpServlet {
    MemberCardService memberService = new MemberCardServiceDatabase();

    /**
     * Sebuah method POST yang akan mengecek apakah member valid atau tidak
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            // Inisialisasi member card
            MemberCard member = memberService.getMemberCardTrue(request.getParameter("memberid"), (int)request.getSession().getAttribute("storeid"));

            // Merubah model menjadi json
            Gson gson = new Gson();
            String json = gson.toJson(member);

            // Mengirimkan kembali kepada AJAX as response
            PrintWriter out = response.getWriter();
            out.print(json);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
