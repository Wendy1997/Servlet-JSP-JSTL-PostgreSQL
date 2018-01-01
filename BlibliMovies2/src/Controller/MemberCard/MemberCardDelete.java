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
import java.sql.SQLException;

/**
 * Sebuah kelas yang menghandle penghapusan ataupun pengembalian member card
 * url: /admin/membercard/delete
 */
@WebServlet("/admin/membercard/delete")
public class MemberCardDelete extends HttpServlet {
    MemberCardService memberCardService = new MemberCardServiceDatabase();

    /**
     * Sebuah method GET yang akan melakukan penghapusan ataupun pengembalian member card
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Initial Address
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
            // Pengambilan data member card yang bersangkutan
            MemberCard memberCard = memberCardService.getMemberCard(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));

            // Pengecekan apakah status member card tersebut aktif atau tidak
            if(memberCard.isStatus()){

                // Jika aktif maka akan didelete
                memberCardService.deleteMemberCard(memberCard.getId() + "", (int)request.getSession().getAttribute("storeid"));
                request.setAttribute("complete", "Deleted");
            } else {

                // Jika pasif maka akan di retrieve
                memberCardService.retrieveMemberCard(memberCard.getId() + "", (int)request.getSession().getAttribute("storeid"));
                request.setAttribute("complete", "Retrieved");
            }

            // Redirect menuju halaman success
            address = "/view/database/success.jsp";
            request.setAttribute("title", "MemberCard");
            request.setAttribute("link", "/admin/membercard");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

