package Controller.MemberCard;

import DAO.MemberCardDAO;
import Model.MemberCard;
import Service.MemberCardService;
import Service.MemberCardServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/membercard")
public class MemberCardMenu extends HttpServlet{
    MemberCardService memberCardService = new MemberCardServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/database/member/member_menu.jsp";

        try{
            List<MemberCard> memberCards = memberCardService.getAllMemberCard();
            request.setAttribute("memberCards", memberCards);

            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
