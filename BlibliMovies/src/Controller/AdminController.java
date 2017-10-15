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

public class AdminController extends HttpServlet{
    AccountDAO accountDAO;

    /**
     * Suatu pengecekan apakah form input terdapat pada database Account
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        accountDAO = new AccountDAO();
        String address = "/view/login/account_login.jsp";

        /*
            Validasi pada database Account
         */
        try{
            Account account = accountDAO.getAccount(request.getParameter("username"));

            if(account != null){
                if(account.getPassword().equals(request.getParameter("password"))){
                    request.getSession().setAttribute("role", account.getRole());
                    address = "/view/menu/admin_menu.jsp";
                }
            }
            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Suatu halaman yang akan meredirect menuju halaman database.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/menu/admin_menu.jsp";

        request.getRequestDispatcher(address).forward(request, response);
    }
}
