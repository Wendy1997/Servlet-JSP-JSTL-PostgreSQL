package Controller.FnBSize;

import DAO.AccountDAO;
import Model.Account;
import Model.AccountRole;
import Model.FnBSize;
import Service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/fnbsize/add")
public class FnBSizeAdd extends HttpServlet {
    FnBService fnbService = new FnBServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/database/fnbsize/fnbsize_add.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storeid") == null){
            address = "/view/login/store_login.jsp";
        }

        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute("role") == null){
            address = "/view/login/account_login.jsp";
        }

        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute("role").equals("admin")){
            address = "/view/login/account_login.jsp";
        }

        try{
            List<FnBSize> fnBSizeList = fnbService.getAllFnBSize((int)request.getSession().getAttribute("storeid"));
            request.setAttribute("size", fnBSizeList);
        }catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            FnBSize fnBSize = new FnBSize( request.getParameter("size"),
                    (int)request.getSession().getAttribute("storeid"));

            fnbService.addFnBSize(fnBSize);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "FnB Size");
            request.setAttribute("complete", "Created");
            request.setAttribute("link", "/admin/fnbsize");

            request.getRequestDispatcher(address).forward(request,response);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
