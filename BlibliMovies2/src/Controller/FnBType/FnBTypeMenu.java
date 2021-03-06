package Controller.FnBType;

import DAO.AccountDAO;
import Model.Account;
import Model.FnBType;
import Service.AccountService;
import Service.AccountServiceDatabase;
import Service.FnBService;
import Service.FnBServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Sebuah kelas yang menghandle list fnbtype
 * url: /admin/fnbtype
 */
@WebServlet("/admin/fnbtype")
public class FnBTypeMenu extends HttpServlet{
    FnBService fnbService = new FnBServiceDatabase();
    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String menuFnBTypeAddress = "/view/database/fnbtype/fnbtype_menu.jsp";
    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";
    private final int initialPage = 0;

    /**
     * Sebuah method GET yang memberikan halaman list fnb type
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
            // Pengambilan data list fnb type untuk diretrieve pada menu
            List<FnBType> fnbTypeList = fnbService.getAllFnBType((int)request.getSession().getAttribute(storeIdSession), initialPage);

            // Pengambilan data jumlah halaman yang akan ditampilkan pada menu
            int pageCounter = fnbService.getCountAllFnBType((int)request.getSession().getAttribute(storeIdSession));

            request.setAttribute("type", fnbTypeList);
            request.setAttribute("page", pageCounter);

            request.getRequestDispatcher(menuFnBTypeAddress).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
