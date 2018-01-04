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

/**
 * Sebuah kelas yang menghandle penghapusan ataupun pengembalian storeaccount
 * url: /admin/storeaccount/delete
 */
@WebServlet("/admin/storeaccount/delete")
public class StoreAccountDelete extends HttpServlet {
    StoreAccountService storeAccountService = new StoreAccountServiceDatabase();
    private final String superLoginAddress = "/view/login/superadmin_login.jsp";
    private final String successAddress = "/view/database/success.jsp";
    private final String superAdminSession = "superadminid";
    private final String title = "Store Account";
    private final String statusDeleteBerhasil = "Deleted";
    private final String statusRetrieveBerhasil = "Retrieved";
    private final String link = "/admin/storeaccount";

    /**
     * Sebuah method GET yang akan melakukan penghapusan ataupun pengembalian store account
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Validasi apakah sudah login as super
        if(request.getSession().getAttribute(superAdminSession) == null){
            request.getRequestDispatcher(superLoginAddress).forward(request, response);
        }

        try{
            // Inisialisasi store account
            StoreAccount storeAccount = storeAccountService.getStoreAccount(request.getParameter("id"));

            // Pengecekan apakah dia aktif atau tidak
            if(storeAccount.getStatus()){

                // Jika aktif maka akan didelete
                storeAccountService.deleteStoreAccount(storeAccount.getId());
                request.setAttribute("complete", statusDeleteBerhasil);
            } else{

                // Jika pasif maka akan diretrieve
                request.setAttribute("complete", statusRetrieveBerhasil);
                storeAccountService.retrieveStoreAccount(storeAccount.getId());
            }

            // Redirect menuju halaman success
            request.setAttribute("title", title);
            request.setAttribute("link", link);

            request.getRequestDispatcher(successAddress).forward(request, response);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
