package Controller.Account;

import DAO.AccountDAO;
import Model.Account;
import Service.AccountService;
import Service.AccountServiceDatabase;
import jdk.nashorn.internal.runtime.ECMAException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/account/delete")
public class AccountDelete extends HttpServlet {
    AccountService accountService = new AccountServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            Account account = accountService.getAccount(request.getParameter("id"));

            accountService.deleteAccount(account.getUsername() + "");

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Account");
            request.setAttribute("complete", "Deleted");
            request.setAttribute("link", "/admin/account");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
