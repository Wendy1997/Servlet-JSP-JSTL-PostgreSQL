package Service;

import DAO.FnBDAO;
import Model.FnB;

import java.sql.SQLException;
import java.util.List;

public class FnBServiceDatabase implements FnBService {
    FnBDAO fnbDAO = new FnBDAO();

    @Override
    public FnB getFnB(String id, String storename) throws SQLException{
        return fnbDAO.getFnB(id, storename);
    }

    @Override
    public List<FnB> getAllFnB(String storename) throws SQLException{
        return fnbDAO.getAllFnB(storename);
    }

    @Override
    public void addFnB(FnB fnb) throws SQLException{
        fnbDAO.addFnB(fnb);
    }

    @Override
    public void deleteFnB(String id, String storename) throws SQLException{
        fnbDAO.deleteFnB(id, storename);
    }

    @Override
    public void updateFnB(FnB fnb) throws SQLException{
        fnbDAO.updateFnB(fnb);
    }
}
