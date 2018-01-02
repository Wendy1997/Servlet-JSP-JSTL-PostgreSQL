package Controller.MemberCard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/membercard/add/success")
public class MemberCardAddSuccess extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // Redirect menuju halaman success
        String address = "/view/database/success.jsp";
        request.setAttribute("title", "MemberCard");
        request.setAttribute("complete", "Created");
        request.setAttribute("link", "/admin/membercard");

        request.getRequestDispatcher(address).forward(request,response);
    }
}
