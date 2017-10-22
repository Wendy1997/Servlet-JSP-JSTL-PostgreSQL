package Controller.MemberCard;

import Model.MemberCard;
import Service.MemberCardService;
import Service.MemberCardServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/membercard/delete")
public class MemberCardDelete extends HttpServlet {
    MemberCardService memberCardService = new MemberCardServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            MemberCard memberCard = memberCardService.getMemberCard(request.getParameter("id"));

            memberCardService.deleteMemberCard(memberCard.getId() + "");

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "MemberCard");
            request.setAttribute("complete", "Deleted");
            request.setAttribute("link", "/admin/membercard");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

