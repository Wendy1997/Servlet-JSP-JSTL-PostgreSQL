package Service;

import DAO.FilmTicketDAO;
import Model.FilmTicket;

import java.sql.SQLException;
import java.util.List;

public class FilmTicketServiceDatabase implements FilmTicketService {
    FilmTicketDAO filmTicketDAO;

    @Override
    public List<FilmTicket> getAllTickets(String film, String studio, String screening, int storeid, String date) throws SQLException {
        filmTicketDAO = new FilmTicketDAO();
        return filmTicketDAO.getAllFilmTicket(film, studio, screening, storeid, date);
    }

    @Override
    public void addTicket(FilmTicket filmTicket) throws SQLException {
        filmTicketDAO = new FilmTicketDAO();
        filmTicketDAO.addFilmTicket(filmTicket);
    }
}
