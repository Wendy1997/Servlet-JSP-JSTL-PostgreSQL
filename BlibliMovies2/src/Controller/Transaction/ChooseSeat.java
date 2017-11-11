package Controller.Transaction;

import Model.Film;
import Model.FilmTicket;
import Model.Seat;
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

        try{

            List<FilmTicket> filmTicketList = filmTicketService.getAllTickets(request.getParameter("id"),request.getParameter("studioid"),request.getParameter("screeningid"),"blibli");
            Film film = filmService.getFilm(request.getParameter("id"), "blibli");
            System.out.println(request.getParameter("id"));
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
            for(int i = 0; i < seatList.length; i++){
                if(!seatList[i].isEmpty())
                    filmTicketService.addTicket(new FilmTicket(Integer.parseInt(request.getParameter("filmid")), Integer.parseInt(request.getParameter("studioid")), seatList[i], Integer.parseInt(request.getParameter("screeningid")), 0, "blibli"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }
}
