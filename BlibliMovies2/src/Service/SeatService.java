package Service;

import Model.Seat;

import java.sql.SQLException;

public interface SeatService {
    Seat getSeat(String id) throws SQLException;
}
