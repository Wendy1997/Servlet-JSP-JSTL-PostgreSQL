package Controller.StoreAccount;

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
import java.util.List;

@WebServlet("/admin/storeaccount/edit")
public class StoreAccountEdit extends HttpServlet{
    StoreAccountService storeAccountService = new StoreAccountServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/storeaccount/storeaccount_edit.jsp";

        try {
            StoreAccount storeAccount = storeAccountService.getStoreAccount(request.getParameter("id"));
            System.out.println(storeAccount.getId());
            request.setAttribute("storeaccount", storeAccount);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            StoreAccount storeAccount = new StoreAccount( request.getParameter("username"),
                    request.getParameter("password").hashCode() + "",
                    request.getParameter("name"),
                    Integer.parseInt(request.getParameter("id")));
            storeAccountService.updateStoreAccount(storeAccount);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Store Account");
            request.setAttribute("complete", "Updated");
            request.setAttribute("link", "/admin/storeaccount");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}