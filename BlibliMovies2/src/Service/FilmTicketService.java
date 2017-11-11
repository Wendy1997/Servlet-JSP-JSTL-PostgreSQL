package Service;

import Model.FilmTicket;

import java.sql.SQLException;
import java.util.List;

public interface FilmTicketService {
    List<FilmTicket> getAllTickets(String film, String studio, String screening, String storename) throws SQLException;
    void  addTicket(FilmTicket filmTicket) throws SQLException;
}
