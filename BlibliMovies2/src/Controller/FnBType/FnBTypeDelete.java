package Controller.FnBType;

import DAO.AccountDAO;
import Model.Account;
import Model.FnBType;
import Service.AccountService;
import Service.AccountServiceDatabase;
import Service.FnBService;
import Service.FnBServiceDatabase;
import jdk.nashorn.internal.runtime.ECMAException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Sebuah kelas yang menghandle penghapusan ataupun pengembalian fnb type
 * url: /admin/fnb/delete
 */
@WebServlet("/admin/fnbtype/delete")
public class FnBTypeDelete extends HttpServlet {
    FnBService fnbService = new FnBServiceDatabase();

    /**
     * Sebuah method GET yang akan melakukan penghapusan ataupun pengembalian fnb type
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Initial Address
        String address = "/view/database/fnbtype/fnbtype_menu.jsp";

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
            // Pengambilan data fnb type yang bersangkutan
            FnBType fnbType = fnbService.getFnBType(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));

            // Pengecekan apakah status fnb type tersebut aktif atau tidak
            if(fnbType.isStatus()){

                // Jika aktif maka akan didelete
                fnbService.deleteFnBType(fnbType.getId() + "", (int)request.getSession().getAttribute("storeid"));
                request.setAttribute("complete", "Deleted");
            } else {

                // Jika pasif maka akan di retrieve
                fnbService.retrieveFnBType(fnbType.getId() + "", (int)request.getSession().getAttribute("storeid"));
                request.setAttribute("complete", "Retrieved");
            }

            // Redirect menuju halaman success
            address = "/view/database/success.jsp";
            request.setAttribute("title", "FnB Type");
            request.setAttribute("link", "/admin/fnbtype");

            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
