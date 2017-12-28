package Controller.Login;

import DAO.AccountDAO;
import Model.AccountRole;
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/login/account_login.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storeid") == null){
            address = "/view/login/store_login.jsp";
        }

        //Validasi jika ingin logout
        if(request.getParameter("page") != null){
            if(request.getParameter("page").equals("logout")){
                request.getSession().removeAttribute("role");
                request.getSession().removeAttribute("username");
                request.getSession().removeAttribute("userid");
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

        try{
            Model.Account account = accountService.getAccount(request.getParameter("username"), (int)request.getSession().getAttribute("storeid"));

            if(account != null){
                if(account.getPassword().equals(request.getParameter("password"))){
                    AccountRole accountRole = accountService.getAccountRole(account.getRoleid(),(int)request.getSession().getAttribute("storeid"));
                    request.getSession().setAttribute("role", accountRole.getRole());
                    request.getSession().setAttribute("username", account.getUsername());
                    request.getSession().setAttribute("userid", account.getid());

                    address = "/view/database/success.jsp";
                    request.setAttribute("title", "Login");
                    request.setAttribute("complete", "Sukses");
                    request.setAttribute("link", "/" + (String)request.getSession().getAttribute("role"));
                }else{
                    address = "/view/database/success.jsp";
                    request.setAttribute("title", "Login");
                    request.setAttribute("complete", "Gagal");
                    request.setAttribute("link", "/login");
                }
            } else{
                address = "/view/database/success.jsp";
                request.setAttribute("title", "Login");
                request.setAttribute("complete", "Gagal");
                request.setAttribute("link", "/login");
            }
            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
