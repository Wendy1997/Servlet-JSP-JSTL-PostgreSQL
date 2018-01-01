package Controller.FnBSize;

import DAO.AccountDAO;
import Model.Account;
import Model.AccountRole;
import Model.FnBSize;
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
 * Sebuah kelas yang menghandle pengeditan fnb size
 * url: /admin/fnbsize/edit
 */
@WebServlet("/admin/fnbsize/edit")
public class FnBSizeEdit extends HttpServlet{
    FnBService fnbService = new FnBServiceDatabase();

    /**
     * Sebuah method GET yang memberikan halaman form edit fnb size
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Initial Address
        String address = "/view/database/fnbsize/fnbsize_edit.jsp";

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

        try {
            // Pengambilan data fnb yang bersangkutan
            FnBSize fnbSize = fnbService.getFnBSize(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));
            request.setAttribute("size", fnbSize);
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman edit fnb size
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            // Inisialisasi FnB size
            FnBSize fnbSize = new FnBSize( Integer.parseInt(request.getParameter("id")),
                    request.getParameter("size"),
                    (int)request.getSession().getAttribute("storeid"));

            // Sebuah method yang akan memasukkan fnb pada database
            fnbService.updateFnBSize(fnbSize);

            // Redirect menuju halaman success
            String address = "/view/database/success.jsp";
            request.setAttribute("title", "FnB Size");
            request.setAttribute("complete", "Updated");
            request.setAttribute("link", "/admin/fnbsize");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (SQLException e){
           e.printStackTrace();
        }
    }
}