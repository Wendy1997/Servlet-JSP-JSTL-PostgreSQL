package Controller.StoreAccount;

import DAO.PromoDAO;
import Model.*;
import Service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/storeaccount/add")
public class StoreAccountAdd extends HttpServlet {
    StoreAccountService storeAccountService = new StoreAccountServiceDatabase();
    AccountService accountService = new AccountServiceDatabase();
    MemberCardService memberCardService = new MemberCardServiceDatabase();
    PromoDAO promoDAO = new PromoDAO();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/database/storeaccount/storeaccount_add.jsp";
        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            StoreAccount storeAccount = new StoreAccount( request.getParameter("username"),
                    request.getParameter("password").hashCode() + "",
                    request.getParameter("name"));

            storeAccountService.addStoreAccount(storeAccount);

            int storeid = storeAccountService.getStoreAccount(request.getParameter("username")).getId();

            AccountRole accountRole = new AccountRole( "admin", storeid);
            accountService.addAccountRole(accountRole);
            accountRole = new AccountRole( "cashier", storeid);
            accountService.addAccountRole(accountRole);

            List<AccountRole> role = accountService.getAllAccountRole(storeid);
            int roleAdmin = 0;
            for(int i = 0; i < role.size(); i++){
                if(role.get(i).getRole().equals("admin")){
                    roleAdmin = role.get(i).getId();
                }
            }

            Account account = new Account( "admin",
                    storeid,
                    "admin".hashCode() + "",
                    roleAdmin);
            accountService.addAccount(account);

            MemberGender memberGender = new MemberGender("Pria", storeid);
            memberCardService.addMemberGender(memberGender);
            memberGender = new MemberGender("Wanita", storeid);
            memberCardService.addMemberGender(memberGender);

            Promo promo = new Promo(storeid, "tes", "tes", 10);
            promoDAO.addPromo(promo);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Store Account");
            request.setAttribute("complete", "Created");
            request.setAttribute("link", "/admin/storeaccount");

            request.getRequestDispatcher(address).forward(request,response);
        } catch (Exception e){
           e.printStackTrace();
        }
    }
}
