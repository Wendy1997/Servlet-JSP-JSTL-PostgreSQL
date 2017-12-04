package Controller.Transaction;

import Model.Film;
import Model.FilmTicket;
import Model.Seat;
import Model.Studio;
import Service.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cashier/seat")
public class ChooseSeat extends HttpServlet {

    FilmService filmService = new FilmServiceDatabase();
    FilmTicketService filmTicketService = new FilmTicketServiceDatabase();
    SeatService seatService = new SeatServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/transaction/choose_seat.jsp";

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

        try{

            List<FilmTicket> filmTicketList = filmTicketService.getAllTickets(request.getParameter("id"),request.getParameter("studioid"),request.getParameter("screeningid"),(String)request.getSession().getAttribute("storename"));
            Film film = filmService.getFilm(request.getParameter("id"), (String)request.getSession().getAttribute("storename"));
            request.setAttribute("filmid", request.getParameter("id"));
            request.setAttribute("studioid", request.getParameter("studioid"));
            request.setAttribute("screeningid", request.getParameter("screeningid"));
            request.setAttribute("filmTickets",filmTicketList);
            request.setAttribute("film", film);
        }catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String address = "/view/transaction/choose_fnb.jsp";

        String[] seatList = request.getParameter("tickets").split(",");

        try {

            Studio studio = filmService.getStudio(request.getParameter("studioid"), (String)request.getSession().getAttribute("storename"));

            for(int i = 0; i < seatList.length; i++){
                if(!seatList[i].isEmpty())
                    filmTicketService.addTicket(new FilmTicket(Integer.parseInt(request.getParameter("filmid")), Integer.parseInt(request.getParameter("studioid")), seatList[i], Integer.parseInt(request.getParameter("screeningid")), studio.getPrice(), (String)request.getSession().getAttribute("storename")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }
}
