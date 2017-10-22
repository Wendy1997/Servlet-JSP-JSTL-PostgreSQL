package Controller.FnB;

import DAO.FnBDAO;
import Model.FnB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.zip.Adler32;

@WebServlet("/admin/fnb/add")
public class FnBAdd extends HttpServlet{
    FnBDAO fnbDAO = new FnBDAO();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/database/fnb/fnb_add.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storename") == null){
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

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            FnB fnb = new FnB((String)request.getSession().getAttribute("storeusername"),
                    request.getParameter("cover"),
                    request.getParameter("name"),
                    request.getParameter("type"),
                    request.getParameter("size"),
                    Integer.parseInt(request.getParameter("price")));

            fnbDAO.addFnB(fnb);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Food and Beverages");
            request.setAttribute("complete", "Created");
            request.setAttribute("link", "/admin/fnb");
            request.getRequestDispatcher(address).forward(request,response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
