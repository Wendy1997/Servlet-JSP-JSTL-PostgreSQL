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

@WebServlet("/cashier/member")
public class ViewMember extends HttpServlet {
    MemberCardService memberService = new MemberCardServiceDatabase();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/transaction/view_member.jsp";

        try{
            MemberCard member = memberService.getMemberCard(request.getParameter("memberid"), (int)request.getSession().getAttribute("storeid"));
            Gson gson = new Gson();
            String json = gson.toJson(member);

            PrintWriter out = response.getWriter();
            out.print(json);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
