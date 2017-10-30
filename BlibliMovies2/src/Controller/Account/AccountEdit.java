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

@WebServlet("/admin/account/edit")
public class AccountEdit extends HttpServlet{
    AccountService accountService = new AccountServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/account/account_edit.jsp";

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
            Account account = accountService.getAccount(request.getParameter("id"), (String)request.getSession().getAttribute("storename"));
            request.setAttribute("account", account);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            Account account = new Account( request.getParameter("username"),
                    (String)request.getSession().getAttribute("storename"),
                    request.getParameter("password"),
                    request.getParameter("role"));
            accountService.updateAccout(account);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Account");
            request.setAttribute("complete", "Updated");
            request.setAttribute("link", "/admin/account");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}