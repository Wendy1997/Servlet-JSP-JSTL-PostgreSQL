package Controller.Transaction;

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

@WebServlet("/check/member")
public class CheckMember extends HttpServlet{

    MemberCardService memberCardService = new MemberCardServiceDatabase();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");

        try {
            MemberCard memberCard = memberCardService.getMemberCard(id, (String)request.getSession().getAttribute("storename"));
            PrintWriter out = response.getWriter();
            if(memberCard == null){
                out.print("0");
            } else {
                out.print("10");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}