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

    /**
     * Sebuah method GET yang memberikan halaman list fnb type
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Initial address
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
            // Pengambilan data list fnb type untuk diretrieve pada menu
            List<FnBType> fnbTypeList = fnbService.getAllFnBType((int)request.getSession().getAttribute("storeid"), 0);

            // Pengambilan data jumlah halaman yang akan ditampilkan pada menu
            int pageCounter = fnbService.getCountAllFnBType((int)request.getSession().getAttribute("storeid"));

            request.setAttribute("type", fnbTypeList);
            request.setAttribute("page", pageCounter);

            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
