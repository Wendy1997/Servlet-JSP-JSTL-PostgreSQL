package Controller.Transaction;

import DAO.PromoDAO;
import Model.FnB;
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

/**
 * Sebuah kelas yang mengecek apakah member tersedia atau tidak
 * url: /check/member
 */
@WebServlet("/check/member")
public class CheckMember extends HttpServlet{
    PromoDAO promoDAO = new PromoDAO();
    MemberCardService memberCardService = new MemberCardServiceDatabase();

    /**
     * Sebuah method POST yang akan mengecek apakah member valid atau tidak
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");

        try {
            // Inisialisasi member card
            MemberCard memberCard = memberCardService.getMemberCardTrue(id, (int)request.getSession().getAttribute("storeid"));

            // Pengecekan apakah valid?
            PrintWriter out = response.getWriter();
            if(memberCard == null){
                out.print("0");
            } else {
                out.print(promoDAO.getPromo((int)request.getSession().getAttribute("storeid")).getDiscountAmount());
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
