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
    StoreAccountDAO storeDAO;

    /**
     * Pengecekan apakah sesuai dengan database StoreAccount
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        storeDAO = new StoreAccountDAO();
        String address = "/view/login/store_login.jsp";

        /*
            Pengecekan form
         */
        try {
            StoreAccount store = storeDAO.getStoreAccount(request.getParameter("username"));
            if(store != null){
                if(store.getPassword().equals(request.getParameter("password"))) {
                    address = "/view/login/account_login.jsp";
                    request.getSession().setAttribute("storename", store.getNama());
                }
            }
            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

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
}
