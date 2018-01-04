package Controller.Transaction;

import Model.*;
import Service.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Sebuah kelas yang menghandle halaman pemilihan seat
 * url: /cashier/seat
 */
@WebServlet("/cashier/seat")
public class ChooseSeat extends HttpServlet {

    FilmService filmService = new FilmServiceDatabase();
    FilmTicketService filmTicketService = new FilmTicketServiceDatabase();

    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String chooseSeatAddress = "/view/transaction/choose_seat.jsp";
    private final String chooseFnBAddress = "/view/transaction/choose_fnb.jsp";
    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";

    /**
     * Sebuah method GET yang akan menampilkan list tempat duduk yang akan dipilih
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute(storeIdSession) == null){
            request.getRequestDispatcher(storeLoginAddress).forward(request, response);
        }

        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute(roleAccountSession) == null){
            request.getRequestDispatcher(accountLoginAddress).forward(request, response);
        }

        try{

            // Pengambilan data kursi yang telah diambil
            List<FilmTicket> filmTicketList = filmTicketService.getAllTickets(request.getParameter("id"),request.getParameter("studioid"),request.getParameter("screeningid"),(int)request.getSession().getAttribute(storeIdSession), request.getParameter("date"));

            // Pengambilan data film yang dipilih
            Film film = filmService.getFilmTrue(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession));
            FilmGenre filmGenre = filmService.getFilmGenre(film.getGenre() + "", (int)request.getSession().getAttribute(storeIdSession));

            request.setAttribute("genre", filmGenre.getGenre());
            request.setAttribute("filmid", request.getParameter("id"));
            request.setAttribute("studioid", request.getParameter("studioid"));
            request.setAttribute("namaStudio", filmService.getStudioTrue(request.getParameter("studioid"), (int)request.getSession().getAttribute(storeIdSession)).getName());
            request.setAttribute("screeningTime", filmService.getScreeningTimeTrue(request.getParameter("screeningid"), request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession)).getTime());
            request.setAttribute("screeningid", request.getParameter("screeningid"));
            request.setAttribute("filmTickets",filmTicketList);
            request.setAttribute("film", film);
            request.setAttribute("date", request.getParameter("date"));
        }catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(chooseSeatAddress).forward(request, response);
    }

    /**
     * Sebuah method POST yang menghandle hasil dari pemilihan tempat duduk
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Inialisasi list seat yang diambil
        String[] seatList = request.getParameter("tickets").split(",");

        try {
            // Inisialisasi studio yang dipilih
            Studio studio = filmService.getStudio(request.getParameter("studioid"), (int)request.getSession().getAttribute(storeIdSession));

            // Looping untuk memasukkan list ticket pada db sesuai dengan film, studio dan screening time yang dipilih
            for(int i = 0; i < seatList.length; i++){
                if(!seatList[i].isEmpty()) {
                    filmTicketService.addTicket(new FilmTicket(Integer.parseInt(request.getParameter("filmid")), Integer.parseInt(request.getParameter("studioid")), seatList[i], Integer.parseInt(request.getParameter("screeningid")), studio.getPrice(), (int) request.getSession().getAttribute(storeIdSession), request.getParameter("date")));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(chooseFnBAddress).forward(request, response);
    }
}
