package Controller.MemberCard;

import DAO.MemberCardDAO;
import Model.MemberCard;
import Model.MemberGender;
import Service.MemberCardService;
import Service.MemberCardServiceDatabase;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Sebuah kelas yang menghandle penambahan member card
 * url: /admin/membercard/add
 */
@WebServlet("/admin/membercard/add")
public class MemberCardAdd extends HttpServlet {
    MemberCardService memberCardService = new MemberCardServiceDatabase();

    /**
     * Sebuah method GET yang memberikan form penambahan member card
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/database/member/member_add.jsp";

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
            List<MemberGender> memberGenderList = memberCardService.getAllMemberGenderTrue((int)request.getSession().getAttribute("storeid"));
            request.setAttribute("gender", memberGenderList);
        }catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman tambah member card
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("masuk");
        try{
            // Inisialisasi member card
            MemberCard memberCard = new MemberCard((int)request.getSession().getAttribute("storeid"),
                    request.getParameter("fullname"),
                    Integer.parseInt(request.getParameter("gender")),
                    request.getParameter("birthdate"),
                    request.getParameter("phonenumber"),
                    request.getParameter("email"));

            // Sebuah method yang akan memasukkan member card pada database
            memberCardService.addMemberCard(memberCard);

            int id = memberCardService.getIDMemberCardTerbaru((int)request.getSession().getAttribute("storeid"));
            memberCard = memberCardService.getMemberCard(id + "", (int)request.getSession().getAttribute("storeid"));

            String hash = memberCard.getId() + memberCard.getFullname() + memberCard.getPhoneNumber();
            int hashCode = hash.hashCode();

            Gson gson = new Gson();
            String json = gson.toJson(memberCard);

            PrintWriter out = response.getWriter();
            out.print("{\"hash\": \"" + hashCode + "\",");
            out.print(" \"result\" : " + json + "}");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
