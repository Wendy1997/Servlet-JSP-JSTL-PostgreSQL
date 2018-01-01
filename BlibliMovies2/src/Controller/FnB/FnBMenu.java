package Controller.FnB;

import DAO.FilmDAO;
import Model.Film;
import Model.FnB;
import Model.FnBSize;
import Model.FnBType;
import Service.FilmService;
import Service.FilmServiceDatabase;
import Service.FnBService;
import Service.FnBServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Sebuah kelas yang menghandle list fnb
 * url: /admin/fnb
 */
@WebServlet("/admin/fnb")
public class FnBMenu extends HttpServlet{
    FnBService fnbService = new FnBServiceDatabase();

    /**
     * Sebuah method GET yang memberikan halaman list fnb
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Initial address
        String address = "/view/database/fnb/fnb_menu.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storeid") == null){
            address = "/view/login/store_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute("role") == null){
            address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute("role").equals("admin")){
            address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        try{

            // Pengambilan data list fnb, size dan juga type fnb untuk diretrieve pada menu
            List<FnB> fnbs = fnbService.getAllFnB((int)request.getSession().getAttribute("storeid"), 0);
            List<FnBSize> fnBSizeList = fnbService.getShowAllFnBSize((int)request.getSession().getAttribute("storeid"));
            List<FnBType> fnBTypeList = fnbService.getShowAllFnBType((int)request.getSession().getAttribute("storeid"));

            // Pengambilan data jumlah halaman yang akan ditampilkan pada menu
            int pageCounter = fnbService.getCountAllFnB((int)request.getSession().getAttribute("storeid"));

            request.setAttribute("size", fnBSizeList);
            request.setAttribute("type", fnBTypeList);
            request.setAttribute("fnbs", fnbs);
            request.setAttribute("page", pageCounter);

            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
