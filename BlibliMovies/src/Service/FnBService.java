package Service;

import DAO.FnBDAO;
import Model.FnB;
import Model.FnB;

import java.sql.SQLException;
import java.util.List;

public interface FnBService {

    FnB getFnB(String id, String storename) throws SQLException;
    List<FnB> getAllFnB(String storename) throws SQLException;
    void addFnB(FnB fnb) throws SQLException;
    void deleteFnB(String id, String storename) throws SQLException;
    void updateFnB(FnB fnb) throws SQLException;
}
