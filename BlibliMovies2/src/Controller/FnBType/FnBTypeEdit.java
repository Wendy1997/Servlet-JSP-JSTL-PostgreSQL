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
import java.util.List;

@WebServlet("/admin/fnbtype/edit")
public class FnBTypeEdit extends HttpServlet{
    FnBService fnbService = new FnBServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/fnbtype/fnbtype_edit.jsp";

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

        try {
            FnBType fnbType = fnbService.getFnBType(request.getParameter("id"), (String)request.getSession().getAttribute("storename"));
            request.setAttribute("type", fnbType);
        } catch (Exception e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            FnBType fnbType = new FnBType( request.getParameter("id"),
                    request.getParameter("type"),
                    (String)request.getSession().getAttribute("storename"));
            fnbService.updateFnBType(fnbType);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "FnB Type");
            request.setAttribute("complete", "Updated");
            request.setAttribute("link", "/admin/fnbtype");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
           e.printStackTrace();
        }
    }
}