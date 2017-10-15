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

    /**
     * Suatu Controller yang akan me redirect menuju halaman Store Login dan melakukan logout
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/login/store_login.jsp";

        /*
            Validasi Log out
         */
        if(request.getParameter("page") != null){
            if(request.getParameter("page").equals("logout")){
                request.getSession().removeAttribute("storename");
            }
        }

        request.getRequestDispatcher(address).forward(request, response);
    }
}
