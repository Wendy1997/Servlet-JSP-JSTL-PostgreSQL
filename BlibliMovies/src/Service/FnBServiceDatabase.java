package Service;

import DAO.FnBDAO;
import Model.FnB;

import java.sql.SQLException;
import java.util.List;

public class FnBServiceDatabase implements FnBService {
    FnBDAO fnbDAO = new FnBDAO();

    public FnB getFnB(String id) throws SQLException{
        return fnbDAO.getFnB(id);
    }

    public List<FnB> getAllFnB() throws SQLException{
        return fnbDAO.getAllFnB();
    }

    public void addFnB(FnB fnb) throws SQLException{
        fnbDAO.addFnB(fnb);
    }

    public void deleteFnB(String id) throws SQLException{
        fnbDAO.deleteFnB(id);
    }

    public void updateFnB(FnB fnb) throws SQLException{
        fnbDAO.updateFnB(fnb);
    }
}
