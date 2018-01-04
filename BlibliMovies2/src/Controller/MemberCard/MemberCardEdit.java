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
 * Sebuah kelas yang menghandle pengeditan member card
 * url: /admin/membercard/edit
 */
@WebServlet("/admin/membercard/edit")
public class MemberCardEdit extends HttpServlet{
    MemberCardService memberCardService = new MemberCardServiceDatabase();

    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String editMemberCardAddress = "/view/database/member/member_edit.jsp";
    private final String successAddress = "/view/database/success.jsp";

    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";

    private final String title = "Member Card";
    private final String statusEditBerhasil = "Updated";
    private final String link = "/admin/membercard";

    /**
     * Sebuah method GET yang memberikan halaman form edit member card
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute(storeIdSession) == null){
            request.getRequestDispatcher(storeLoginAddress).forward(request, response);
        }
        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute(roleAccountSession) == null){
            request.getRequestDispatcher(accountLoginAddress).forward(request, response);
        }
        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute(roleAccountSession).equals(roleAdmin)){
            request.getRequestDispatcher(accountLoginAddress).forward(request, response);
        }


        try {
            // Pengambilan data member card yang bersangkutan
            MemberCard memberCard = memberCardService.getMemberCard(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession));
            request.setAttribute("memberCard", memberCard);

            // Pengambilan seluruh gender yang akan ditampilkan pada form
            List<MemberGender> memberGenderList = memberCardService.getAllMemberGenderTrue((int)request.getSession().getAttribute(storeIdSession));
            request.setAttribute("gender", memberGenderList);

            // Validasi jika id tersedia atau tidak
            memberCard.toString();

        } catch (Exception e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(editMemberCardAddress).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman edit member card
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            // Inisialisasi Member Card
            MemberCard memberCard = new MemberCard(
                    Integer.parseInt(request.getParameter("id")),
                    (int)request.getSession().getAttribute("storeid"),
                    request.getParameter("fullname"),
                    Integer.parseInt(request.getParameter("gender")),
                    request.getParameter("birthdate"),
                    request.getParameter("phonenumber"),
                    request.getParameter("email"));

            // Sebuah method yang akan memasukkan member card pada database
            memberCardService.updateAccout(memberCard);

            // Redirect menuju halaman success
            request.setAttribute("title", title);
            request.setAttribute("complete", statusEditBerhasil);
            request.setAttribute("link", link);

            request.getRequestDispatcher(successAddress).forward(request, response);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}