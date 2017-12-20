package Controller.Ledger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/admin/ledger")
public class LedgerView extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/database/ledger/ledger_menu.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storename") == null){
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

        LocalDate now = LocalDate.now();
        String input = now.toString();
        String format = "yyyy-MM-dd";
        String week = "";
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);
            Date date = df.parse(input);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            week = cal.get(Calendar.WEEK_OF_YEAR) + "";
            if(Integer.parseInt(week) < 10){
                week = "0" + week;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String[] todayList = input.split("-");
        String day = todayList[2];
        String month = todayList[1];
        String year = todayList[0];

        request.setAttribute("day", day);
        request.setAttribute("month", month);
        request.setAttribute("year", year);
        request.setAttribute("week", week);
        request.getRequestDispatcher(address).forward(request, response);
    }

}