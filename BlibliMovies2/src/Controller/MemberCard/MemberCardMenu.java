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
    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String menuMemberCardAddress = "/view/database/member/member_menu.jsp";
    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";
    private final int initialPage = 0;

    /**
     * Sebuah method GET yang memberikan halaman list member card
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

        try{
            // Pengambilan data list member card dan gender untuk diretrieve pada menu
            List<MemberCard> memberCards = memberCardService.getAllMemberCard((int)request.getSession().getAttribute(storeIdSession), initialPage);
            List<MemberGender> memberGenderList = memberCardService.getAllMemberGender((int)request.getSession().getAttribute(storeIdSession));

            // Pengambilan data jumlah halaman yang akan ditampilkan pada menu
            int pageCounter = memberCardService.getCountAllMemberCard((int)request.getSession().getAttribute(storeIdSession));

            request.setAttribute("gender", memberGenderList);
            request.setAttribute("memberCards", memberCards);
            request.setAttribute("page", pageCounter);

            request.getRequestDispatcher(menuMemberCardAddress).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
