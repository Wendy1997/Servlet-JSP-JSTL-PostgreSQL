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

/**
 * Sebuah kelas yang menghandle list invoice pada setiap hari, minggu, bulan atau tahun
 * url: /admin/ledger
 */
@WebServlet("/admin/ledger")
public class LedgerView extends HttpServlet{

    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String viewLedgerAddress = "/view/database/ledger/ledger_menu.jsp";
    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";

    /**
     * Sebuah method GET yang memberikan halaman list invoice pada setiap hari, minggu, bulan atau tahun
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


        // Pengambilan data waktu saat ini
        LocalDate now = LocalDate.now();
        String input = now.toString();
        String format = "yyyy-MM-dd";

        // Pengambilan data minggu saat ini
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

        // Pengambilan data hari, bulan dan tahun ini
        String[] todayList = input.split("-");
        String day = todayList[2];
        String month = todayList[1];
        String year = todayList[0];

        request.setAttribute("day", day);
        request.setAttribute("month", month);
        request.setAttribute("year", year);
        request.setAttribute("week", week);

        request.getRequestDispatcher(viewLedgerAddress).forward(request, response);
    }

}
