package Service;

import DAO.FnBDAO;
import DAO.FnBSizeDAO;
import DAO.FnBTypeDAO;
import Model.FnB;
import Model.FnBSize;
import Model.FnBType;

import java.sql.SQLException;
import java.util.List;

public class FnBServiceDatabase implements FnBService {
    FnBDAO fnbDAO = new FnBDAO();
    FnBSizeDAO fnBSizeDAO = new FnBSizeDAO();
    FnBTypeDAO fnBTypeDAO = new FnBTypeDAO();

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

    @Override
    public FnBSize getFnBSize(String id, String storename) throws SQLException {
        return fnBSizeDAO.getFnBSize(id, storename);
    }

    @Override
    public List<FnBSize> getAllFnBSize(String storename) throws SQLException {
        return fnBSizeDAO.getAllFnBSize(storename);
    }

    @Override
    public void addFnBSize(FnBSize fnBSize) throws SQLException {
        fnBSizeDAO.addFnBSize(fnBSize);
    }

    @Override
    public void deleteFnBSize(String fnBSize, String storename) throws SQLException {
        fnBSizeDAO.deleteFnBSize(fnBSize, storename);
    }

    @Override
    public void updateFnBSize(FnBSize fnBSize) throws SQLException {
        fnBSizeDAO.updateFnBSize(fnBSize);
    }

    @Override
    public FnBType getFnBType(String id, String storename) throws SQLException {
        return fnBTypeDAO.getFnBType(id, storename);
    }

    @Override
    public List<FnBType> getAllFnBType(String storename) throws SQLException {
        return fnBTypeDAO.getAllFnBType(storename);
    }

    @Override
    public void addFnBType(FnBType fnBType) throws SQLException {
        fnBTypeDAO.addFnBType(fnBType);
    }

    @Override
    public void deleteFnBType(String fnBType, String storename) throws SQLException {
        fnBTypeDAO.deleteFnBType(fnBType, storename);
    }

    @Override
    public void updateFnBType(FnBType fnBType) throws SQLException {
        fnBTypeDAO.updateFnBType(fnBType);
    }
}
