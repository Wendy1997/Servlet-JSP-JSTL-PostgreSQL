package Controller;

import DAO.StoreAccountDAO;
import Model.StoreAccount;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class StoreController extends HttpServlet{
    StoreAccountDAO storeDAO;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        storeDAO = new StoreAccountDAO();
        String address = "/view/login/store_login.jsp";

        try {
            StoreAccount store = storeDAO.getStoreAccount(request.getParameter("username"));
            if(store != null){
                if(store.getPassword().equals(request.getParameter("password"))) {
                    address = "/view/login/account_login.jsp";
                    request.setAttribute("name", store.getNama());
                }
            }
            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/login/store_login.jsp";
        request.getRequestDispatcher(address).forward(request, response);
    }
}
