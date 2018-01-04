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

/**
 * Sebuah kelas yang menghandle pengeditan storeaccount
 * url: /admin/storeaccount/edit
 */
@WebServlet("/admin/storeaccount/edit")
public class StoreAccountEdit extends HttpServlet{
    StoreAccountService storeAccountService = new StoreAccountServiceDatabase();
    private final String superLoginAddress = "/view/login/superadmin_login.jsp";
    private final String successAddress = "/view/database/success.jsp";
    private final String editStoreAccountAddress = "/view/database/storeaccount/storeaccount_edit.jsp";
    private final String superAdminSession = "superadminid";
    private final String title = "Store Account";
    private final String statusEditBerhasil = "Updated";
    private final String link = "/admin/storeaccount";

    /**
     * Sebuah method GET yang memberikan halaman form edit storeaccount
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

        try {
            // Pengambilan data store account yang bersangkutan
            StoreAccount storeAccount = storeAccountService.getStoreAccount(request.getParameter("id"));
            request.setAttribute("storeaccount", storeAccount);

            // Validasi apakah store account tersedia
            storeAccount.toString();
        } catch (Exception e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(editStoreAccountAddress).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman edit store account
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            // Pengecekan apakah password diubah?
            if(request.getParameter("password").length() > 0){
                StoreAccount storeAccount = new StoreAccount( request.getParameter("username"),
                        request.getParameter("password").hashCode() + "",
                        request.getParameter("name"),
                        Integer.parseInt(request.getParameter("id")));

                // Update dengan password
                storeAccountService.updateStoreAccount(storeAccount);
            } else {
                StoreAccount storeAccount = new StoreAccount( request.getParameter("username"),
                        request.getParameter("name"),
                        Integer.parseInt(request.getParameter("id")));

                // Update tanpa password
                storeAccountService.updateStoreAccountWithoutPass(storeAccount);
            }

            // Redirect menuju halaman success
            request.setAttribute("title", title);
            request.setAttribute("complete", statusEditBerhasil);
            request.setAttribute("link", link);

            request.getRequestDispatcher(successAddress).forward(request, response);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}