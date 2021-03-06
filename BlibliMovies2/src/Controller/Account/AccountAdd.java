package Controller.Account;

import DAO.AccountDAO;
import Model.Account;
import Model.AccountRole;
import Service.AccountService;
import Service.AccountServiceDatabase;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Sebuah kelas yang menghandle penambahan akun
 * url: /admin/account/add
 */
@WebServlet("/admin/account/add")
public class AccountAdd extends HttpServlet {
    private AccountService accountService = new AccountServiceDatabase();
    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String addAccountAddress = "/view/database/account/account_add.jsp";
    private final String successAddress = "/view/database/success.jsp";
    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";
    private final String title = "Account";
    private final String statusBerhasil = "Created";
    private final String statusGagal = "Has Taken";
    private final String link = "/admin/account";

    /**
     * Sebuah method GET yang memberikan halaman form tambah akun
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute(storeIdSession) == null){
            request.getRequestDispatcher(storeLoginAddress).forward(request, response);
        }
        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute(roleAccountSession) == null){
            request.getRequestDispatcher(accountLoginAddress).forward(request, response);
        }
        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute(roleAccountSession).equals(roleAdmin)){
            request.getRequestDispatcher(accountLoginAddress).forward(request, response);
        }

        try{
            // Pengambilan data seluruh role akun untuk dimasukkan kedalam form
            List<AccountRole> accountRoleList = accountService.getAllAccountRoleTrue((int)request.getSession().getAttribute(storeIdSession));
            request.setAttribute("role", accountRoleList);
        }catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(addAccountAddress).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman tambah akun
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            // Inisialisasi Account dari hasil form
            Account account = new Account(
                    request.getParameter("username"),
                    (int)request.getSession().getAttribute(storeIdSession),
                    request.getParameter("password").hashCode() + "",
                    Integer.parseInt(request.getParameter(roleAccountSession))
            );

            // Sebuah pengecekan apakah username telah tersedia
            try{
                accountService.getAccount(request.getParameter("username"), (int)request.getSession().getAttribute(storeIdSession)).getUsername().length();

                // Redirect menuju halaman success
                request.setAttribute("title", title);
                request.setAttribute("complete", statusGagal);
                request.setAttribute("link", link);

            } catch (NullPointerException e){
                // Sebuah method yang akan memasukkan akun ke dalam database
                accountService.addAccount(account);

                // Redirect menuju halaman success
                request.setAttribute("title", title);
                request.setAttribute("complete", statusBerhasil);
                request.setAttribute("link", link);
            } finally {
                request.getRequestDispatcher(successAddress).forward(request,response);
            }
        } catch (SQLException e){
           e.printStackTrace();
        }
    }
}
