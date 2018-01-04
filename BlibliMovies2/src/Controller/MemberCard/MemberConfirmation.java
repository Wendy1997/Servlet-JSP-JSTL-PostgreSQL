package Controller.MemberCard;

import DAO.MemberCardDAO;
import Model.MemberCard;
import Model.MemberGender;
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
 * Sebuah kelas yang menghandle konfirmasi email member card
 * url: /admin/membercard/add
 */
@WebServlet("/confirmation")
public class MemberConfirmation extends HttpServlet {
    MemberCardService memberCardService = new MemberCardServiceDatabase();
    private final String successAddress = "/view/database/success.jsp";

    /**
     * Sebuah method GET yang akan memvalidasi link konfirmasi sesuai dengan member tersebut
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Menyiapkan hash dan id member
        int hash = Integer.parseInt(request.getParameter("code"));
        String id = request.getParameter("id");

        // Redirect default
        request.setAttribute("title", "Confirmation");
        request.setAttribute("complete", "Failed");
        request.setAttribute("link", "/index");

        try{
            // Pengecekan member dan membuat hash dari data member
            MemberCard memberCard = memberCardService.getMemberCard(id);
            String member = memberCard.getId() + memberCard.getFullname() + memberCard.getPhoneNumber();
            int hashCode = member.hashCode();

            // Penyesuaian link dengan hash
            if(hash == hashCode){

                // Membuat akun menjadi aktif
                memberCardService.retrieveMemberCard(id);

                request.setAttribute("complete", "Success");
                request.setAttribute("link", "/index");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(successAddress).forward(request, response);
    }
}
