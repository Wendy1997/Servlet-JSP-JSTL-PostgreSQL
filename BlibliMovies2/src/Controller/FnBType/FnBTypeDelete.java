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

@WebServlet("/admin/fnbtype/delete")
public class FnBTypeDelete extends HttpServlet {
    FnBService fnbService = new FnBServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/fnbtype/fnbtype_menu.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storename") == null){
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
            FnBType fnbType = fnbService.getFnBType(request.getParameter("id"), (String)request.getSession().getAttribute("storename"));

            fnbService.deleteFnBType(fnbType.getId() + "", (String)request.getSession().getAttribute("storename"));

            address = "/view/database/success.jsp";
            request.setAttribute("title", "FnB Type");
            request.setAttribute("complete", "Deleted");
            request.setAttribute("link", "/admin/fnbtype");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
