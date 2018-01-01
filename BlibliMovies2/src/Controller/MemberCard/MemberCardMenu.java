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
import java.sql.SQLException;
import java.util.List;

/**
 * Sebuah kelas yang menghandle list member card
 * url: /admin/membercard
 */
@WebServlet("/admin/membercard")
public class MemberCardMenu extends HttpServlet{
    MemberCardService memberCardService = new MemberCardServiceDatabase();

    /**
     * Sebuah method GET yang memberikan halaman list member card
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Initial address
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
            // Pengambilan data list member card dan gender untuk diretrieve pada menu
            List<MemberCard> memberCards = memberCardService.getAllMemberCard((int)request.getSession().getAttribute("storeid"), 0);
            List<MemberGender> memberGenderList = memberCardService.getAllMemberGender((int)request.getSession().getAttribute("storeid"));

            // Pengambilan data jumlah halaman yang akan ditampilkan pada menu
            int pageCounter = memberCardService.getCountAllMemberCard((int)request.getSession().getAttribute("storeid"));

            request.setAttribute("gender", memberGenderList);
            request.setAttribute("memberCards", memberCards);
            request.setAttribute("page", pageCounter);

            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
