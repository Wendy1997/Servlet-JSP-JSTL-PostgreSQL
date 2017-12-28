package Controller.Login;

import DAO.StoreAccountDAO;
import Model.StoreAccount;
import Service.StoreAccountService;
import Service.StoreAccountServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/")
public class Store extends HttpServlet {
    StoreAccountService storeAccountService = new StoreAccountServiceDatabase();

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
                request.getSession().removeAttribute("storeid");
                request.getSession().removeAttribute("storename");
                request.getSession().removeAttribute("role");
                request.getSession().removeAttribute("username");
            }
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    /**
     * Pengecekan apakah sesuai dengan database StoreAccount
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/login/store_login.jsp";

        /*
            Pengecekan form
         */
        try {
            StoreAccount store = storeAccountService.getStoreAccount(request.getParameter("username"));
            if(store != null){
                if(store.getPassword().equals(request.getParameter("password"))) {
                    request.getSession().setAttribute("storeid", store.getID());
                    request.getSession().setAttribute("storename", store.getNama());

                    address = "/view/database/success.jsp";
                    request.setAttribute("title", "Login");
                    request.setAttribute("complete", "Sukses");
                    request.setAttribute("link", "/login");

                } else{
                    address = "/view/database/success.jsp";
                    request.setAttribute("title", "Login");
                    request.setAttribute("complete", "Gagal");
                    request.setAttribute("link", "/login");
                }
            } else {
                address = "/view/database/success.jsp";
                request.setAttribute("title", "Login");
                request.setAttribute("complete", "Gagal");
                request.setAttribute("link", "/login");
            }
            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
