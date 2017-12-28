package Service;

import DAO.FilmTicketDAO;
import Model.FilmTicket;

import java.sql.SQLException;
import java.util.List;

public class FilmTicketServiceDatabase implements FilmTicketService {
    FilmTicketDAO filmTicketDAO = new FilmTicketDAO();

    @Override
    public List<FilmTicket> getAllTickets(String film, String studio, String screening, int storeid) throws SQLException {
        return filmTicketDAO.getAllFilmTicket(film, studio, screening, storeid);
    }

    @Override
    public void addTicket(FilmTicket filmTicket) throws SQLException {
        filmTicketDAO.addFilmTicket(filmTicket);
    }
}
