package Controller.Login;

import DAO.AccountDAO;
import Service.AccountService;
import Service.AccountServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class Account extends HttpServlet {
    AccountService accountService = new AccountServiceDatabase();

    /**
     * Validasi apakah sudah login, untuk mengakses halaman login, dan Logout
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/login/account_login.jsp";

        /*
            Validasi apakah sudah login
         */
        if(request.getSession().getAttribute("storename") == null){
            address = "/view/login/store_login.jsp";
        }

        /*
            Validasi jika ingin logout
         */
        if(request.getParameter("page") != null){
            if(request.getParameter("page").equals("logout")){
                request.getSession().removeAttribute("role");
            }
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    /**
     * Suatu pengecekan apakah form input terdapat pada database Account
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/login/account_login.jsp";

        /*
            Validasi pada database Account
         */
        try{
            Model.Account account = accountService.getAccount(request.getParameter("username"));

            if(account != null){
                if(account.getPassword().equals(request.getParameter("password"))){
                    request.getSession().setAttribute("role", account.getRole());
                    address = "/view/menu/admin_menu.jsp";
                }
            }
            response.sendRedirect("/admin");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
