package Controller.MemberCard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Sebuah kelas yang akan merefer ke halaman sukses
 *
 * url: /admin/membercard/add/success
 */
@WebServlet("/admin/membercard/add/success")
public class MemberCardAddSuccess extends HttpServlet {

    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String successAddress = "/view/database/success.jsp";
    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";
    private final String title = "Member Card";
    private final String statusAddBerhasil = "Created";
    private final String linkAdmin = "/admin/membercard";
    private final String linkCashier = "/cashier";

    /**
     * Sebuah method yang akan merefer halaman menuju halaman sukses
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
        if(request.getSession().getAttribute(roleAccountSession).equals(roleAdmin)){
            request.setAttribute("link", linkAdmin);
        } else {
            request.setAttribute("link", linkCashier);
        }

        // Redirect menuju halaman success
        request.setAttribute("title", title);
        request.setAttribute("complete", statusAddBerhasil);

        request.getRequestDispatcher(successAddress).forward(request,response);
    }
}
