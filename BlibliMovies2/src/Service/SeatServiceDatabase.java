package Service;

import DAO.SeatDAO;
import Model.Seat;

import java.sql.SQLException;

public class SeatServiceDatabase implements SeatService {

    SeatDAO seatDAO = new SeatDAO();

    @Override
    public Seat getSeat(String id) throws SQLException {
        return seatDAO.getSeat(id);
    }
}
