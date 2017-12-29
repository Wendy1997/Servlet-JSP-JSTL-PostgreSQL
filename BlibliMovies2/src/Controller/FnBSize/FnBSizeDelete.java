package Controller.FnBSize;

import DAO.AccountDAO;
import Model.Account;
import Model.FnBSize;
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

@WebServlet("/admin/fnbsize/delete")
public class FnBSizeDelete extends HttpServlet {
    FnBService fnbService = new FnBServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/fnbsize/fnbsize_menu.jsp";

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
            FnBSize fnbSize = fnbService.getFnBSize(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));

            if(fnbSize.isStatus()){
                fnbService.deleteFnBSize(fnbSize.getId() + "", (int)request.getSession().getAttribute("storeid"));
            } else {
                fnbService.retrieveFnBSize(fnbSize.getId() + "", (int)request.getSession().getAttribute("storeid"));
            }

            address = "/view/database/success.jsp";
            request.setAttribute("title", "FnB Size");
            request.setAttribute("complete", "Deleted");
            request.setAttribute("link", "/admin/fnbsize");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
