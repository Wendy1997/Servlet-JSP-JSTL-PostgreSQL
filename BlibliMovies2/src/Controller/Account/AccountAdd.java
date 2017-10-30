package Controller.Account;

import DAO.AccountDAO;
import Model.Account;
import Service.AccountService;
import Service.AccountServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/account/add")
public class AccountAdd extends HttpServlet {
    AccountService accountService = new AccountServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/database/account/account_add.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storename") == null){
            address = "/view/login/store_login.jsp";
        }

        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute("role") == null){
            address = "/view/login/account_login.jsp";
        }

        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute("role").equals("admin")){
            address = "/view/login/account_login.jsp";
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            Account account = new Account( request.getParameter("username"),
                    (String)request.getSession().getAttribute("storename"),
                    request.getParameter("password"),
                    request.getParameter("role"));

            accountService.addAccount(account);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Account");
            request.setAttribute("complete", "Created");
            request.setAttribute("link", "/admin/account");

            request.getRequestDispatcher(address).forward(request,response);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}