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
 * Sebuah kelas yang menghandle penambahan member card
 * url: /admin/membercard/add
 */
@WebServlet("/confirmation")
public class MemberConfirmation extends HttpServlet {
    MemberCardService memberCardService = new MemberCardServiceDatabase();

    /**
     * Sebuah method GET yang memberikan form penambahan member card
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int hash = Integer.parseInt(request.getParameter("code"));
        String id = request.getParameter("id");
        System.out.println(id);

        // Redirect menuju halaman success
        String address = "/view/database/success.jsp";
        request.setAttribute("title", "Confirmation");
        request.setAttribute("complete", "Failed");
        request.setAttribute("link", "/index");

        try{
            MemberCard memberCard = memberCardService.getMemberCard(id, (int)request.getSession().getAttribute("storeid"));
            String member = memberCard.getId() + memberCard.getFullname() + memberCard.getPhoneNumber();
            int hashCode = member.hashCode();

            System.out.println(hash);
            System.out.println(hashCode);

            if(hash == hashCode){
                memberCardService.retrieveMemberCard(id, (int)request.getSession().getAttribute("storeid"));

                request.setAttribute("title", "Confirmation");
                request.setAttribute("complete", "Success");
                request.setAttribute("link", "/index");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }
}
