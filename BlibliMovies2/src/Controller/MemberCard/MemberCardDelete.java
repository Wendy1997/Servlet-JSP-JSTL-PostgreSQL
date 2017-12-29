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

        String address = "/view/database/member/member_menu.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storeid") == null){
            address = "/view/login/store_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute("role") == null){
            address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute("role").equals("admin")){
            address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        try{
            MemberCard memberCard = memberCardService.getMemberCard(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));

            if(memberCard.isStatus()){
                memberCardService.deleteMemberCard(memberCard.getId() + "", (int)request.getSession().getAttribute("storeid"));
                request.setAttribute("complete", "Deleted");
            } else {
                memberCardService.retrieveMemberCard(memberCard.getId() + "", (int)request.getSession().getAttribute("storeid"));
                request.setAttribute("complete", "Retrieved");
            }

            address = "/view/database/success.jsp";
            request.setAttribute("title", "MemberCard");
            request.setAttribute("link", "/admin/membercard");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

