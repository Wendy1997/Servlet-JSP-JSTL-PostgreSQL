package Controller.FnBType;

import DAO.AccountDAO;
import Model.Account;
import Model.AccountRole;
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
 * Sebuah kelas yang menghandle pengeditan fnb type
 * url: /admin/fnbtype/edit
 */
@WebServlet("/admin/fnbtype/edit")
public class FnBTypeEdit extends HttpServlet{
    FnBService fnbService = new FnBServiceDatabase();

    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String editFnBTypeAddress = "/view/database/fnbtype/fnbtype_edit.jsp";
    private final String successAddress = "/view/database/success.jsp";

    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";

    private final String title = "FnB Type";
    private final String statusEditBerhasil = "Updated";
    private final String link = "/admin/fnbtype";

    /**
     * Sebuah method GET yang memberikan halaman form edit fnb type
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
            // Pengambilan data fnb type yang bersangkutan
            FnBType fnbType = fnbService.getFnBType(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession));
            request.setAttribute("type", fnbType);

            // Validasi jika id tersedia atau tidak
            fnbType.toString();

        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(editFnBTypeAddress).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman edit fnb type
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            // Inisialisasi FnB
            FnBType fnbType = new FnBType( Integer.parseInt(request.getParameter("id")),
                    request.getParameter("type"),
                    (int)request.getSession().getAttribute(storeIdSession));

            // Sebuah method yang akan memasukkan fnb pada database
            fnbService.updateFnBType(fnbType);

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