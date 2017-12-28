package Controller.MemberCard;

import DAO.MemberCardDAO;
import Model.MemberCard;
import Model.MemberGender;
import Service.MemberCardService;
import Service.MemberCardServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/membercard/edit")
public class MemberCardEdit extends HttpServlet{
    MemberCardService memberCardService = new MemberCardServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/member/member_edit.jsp";

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

        try {
            MemberCard memberCard = memberCardService.getMemberCard(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));
            request.setAttribute("memberCard", memberCard);

            List<MemberGender> memberGenderList = memberCardService.getAllMemberGender((int)request.getSession().getAttribute("storeid"));
            request.setAttribute("gender", memberGenderList);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            MemberCard memberCard = new MemberCard( Integer.parseInt(request.getParameter("id")),
                    (int)request.getSession().getAttribute("storeid"),
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