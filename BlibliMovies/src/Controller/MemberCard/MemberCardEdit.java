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

@WebServlet("/admin/membercard/edit")
public class MemberCardEdit extends HttpServlet{
    MemberCardService memberCardService = new MemberCardServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/member/member_edit.jsp";

        try {
            MemberCard memberCard = memberCardService.getMemberCard(request.getParameter("id"));
            request.setAttribute("memberCard", memberCard);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            MemberCard memberCard = new MemberCard( Integer.parseInt(request.getParameter("id")),
                    (String)request.getSession().getAttribute("storename"),
                    request.getParameter("fullname"),
                    request.getParameter("gender"),
                    request.getParameter("birthdate"),
                    request.getParameter("phonenumber"),
                    request.getParameter("email"));

            memberCardService.updateAccout(memberCard);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "MemberCard");
            request.setAttribute("complete", "Updated");
            request.setAttribute("link", "/admin/membercard");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}