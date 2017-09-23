package Controller;

import DAO.AccountDAO;
import DAO.StoreAccountDAO;
import Model.Account;
import Model.StoreAccount;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AccountController extends HttpServlet{
    AccountDAO accountDAO;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        accountDAO = new AccountDAO();
        String address = "/view/login/account_login.jsp";

        try{
            Account account = accountDAO.getAccount(request.getParameter("username"));

            if(account != null){
                if(account.getPassword().equals(request.getParameter("password"))){
                    address = "/view/login/success.jsp";
                    request.setAttribute("name", "ulululululu");
                }
                System.out.println(account.getUsername() + account.getPassword() + request.getParameter("password"));
            }
            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
