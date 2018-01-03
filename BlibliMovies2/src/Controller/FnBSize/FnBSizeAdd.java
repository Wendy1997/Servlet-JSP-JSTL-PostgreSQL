package Controller.FnBSize;

import DAO.AccountDAO;
import Model.Account;
import Model.AccountRole;
import Model.FnBSize;
import Service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Sebuah kelas yang menghandle penambahan fnb size
 * url: /admin/fnbsize/add
 */
@WebServlet("/admin/fnbsize/add")
public class FnBSizeAdd extends HttpServlet {
    FnBService fnbService = new FnBServiceDatabase();

    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String addFnBSizeAddress = "/view/database/fnbsize/fnbsize_add.jsp";
    private final String successAddress = "/view/database/success.jsp";

    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";

    private final String title = "FnB Size";
    private final String statusAddBerhasil = "Created";
    private final String link = "/admin/fnbsize";

    /**
     * Sebuah method GET yang memberikan form penambahan fnb size
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


        request.getRequestDispatcher(addFnBSizeAddress).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman tambah fnb size
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            // Inisialisasi FnB size
            FnBSize fnBSize = new FnBSize( request.getParameter("size"),
                    (int)request.getSession().getAttribute(storeIdSession));

            // Sebuah method yang akan memasukkan fnb size pada database
            fnbService.addFnBSize(fnBSize);

            // Redirect menuju halaman success
            request.setAttribute("title", title);
            request.setAttribute("complete", statusAddBerhasil);
            request.setAttribute("link", link);

            request.getRequestDispatcher(successAddress).forward(request,response);
        } catch (SQLException e){
           e.printStackTrace();
        }
    }
}
