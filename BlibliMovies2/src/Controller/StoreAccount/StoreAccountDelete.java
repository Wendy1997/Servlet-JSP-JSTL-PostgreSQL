package Controller.StoreAccount;

import DAO.StoreAccountDAO;
import Model.StoreAccount;
import Service.StoreAccountService;
import Service.StoreAccountServiceDatabase;
import jdk.nashorn.internal.runtime.ECMAException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/storeaccount/delete")
public class StoreAccountDelete extends HttpServlet {
    StoreAccountService storeAccountService = new StoreAccountServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/storeaccount/storeaccount_menu.jsp";

        try{
            StoreAccount storeAccount = storeAccountService.getStoreAccount(request.getParameter("id"));

            System.out.println(storeAccount.getStatus());
            if(storeAccount.getStatus()){
                storeAccountService.deleteStoreAccount(storeAccount.getId());
                request.setAttribute("complete", "Deleted");
            } else{
                request.setAttribute("complete", "Retrieved");
                storeAccountService.retrieveStoreAccount(storeAccount.getId());
            }

            address = "/view/database/success.jsp";
            request.setAttribute("title", "Store Account");
            request.setAttribute("link", "/admin/storeaccount");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
