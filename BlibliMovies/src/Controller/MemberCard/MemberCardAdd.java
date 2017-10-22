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

@WebServlet("/admin/membercard/add")
public class MemberCardAdd extends HttpServlet {
    MemberCardService memberCardService = new MemberCardServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/database/member/member_add.jsp";
        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            MemberCard memberCard = new MemberCard( (String)request.getSession().getAttribute("storename"),
                    request.getParameter("fullname"),
                    request.getParameter("gender"),
                    request.getParameter("birthdate"),
                    request.getParameter("phonenumber"),
                    request.getParameter("email"));

            memberCardService.addMemberCard(memberCard);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "MemberCard");
            request.setAttribute("complete", "Created");
            request.setAttribute("link", "/admin/membercard");

            request.getRequestDispatcher(address).forward(request,response);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
