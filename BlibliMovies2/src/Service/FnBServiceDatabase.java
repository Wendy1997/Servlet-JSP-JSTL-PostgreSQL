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
    public FnB getFnB(String id, int storeid) throws SQLException{
        return fnbDAO.getFnB(id, storeid);
    }

    @Override
    public FnB getFnBTrue(String id, int storeid) throws SQLException {
        return fnbDAO.getFnBTrue(id, storeid);
    }

    @Override
    public List<FnB> getAllFnB(int storeid) throws SQLException{
        return fnbDAO.getAllFnB(storeid);
    }

    @Override
    public List<FnB> getAllFnBTrue(int storeid) throws SQLException {
        return fnbDAO.getAllFnBTrue(storeid);
    }

    @Override
    public void addFnB(FnB fnb) throws SQLException{
        fnbDAO.addFnB(fnb);
    }

    @Override
    public void deleteFnB(String id, int storeid) throws SQLException{
        fnbDAO.deleteFnB(id, storeid);
    }

    @Override
    public void updateFnB(FnB fnb) throws SQLException{
        fnbDAO.updateFnB(fnb);
    }

    @Override
    public FnBSize getFnBSize(String id, int storeid) throws SQLException {
        return fnBSizeDAO.getFnBSize(id, storeid);
    }

    @Override
    public FnBSize getFnBSizeTrue(String id, int storeid) throws SQLException {
        return fnBSizeDAO.getFnBSizeTrue(id, storeid);
    }

    @Override
    public List<FnBSize> getAllFnBSize(int storeid) throws SQLException {
        return fnBSizeDAO.getAllFnBSize(storeid);
    }

    @Override
    public List<FnBSize> getAllFnBSizeTrue(int storeid) throws SQLException {
        return fnBSizeDAO.getAllFnBSizeTrue(storeid);
    }

    @Override
    public void addFnBSize(FnBSize fnBSize) throws SQLException {
        fnBSizeDAO.addFnBSize(fnBSize);
    }

    @Override
    public void deleteFnBSize(String fnBSize, int storeid) throws SQLException {
        fnBSizeDAO.deleteFnBSize(fnBSize, storeid);
    }

    @Override
    public void updateFnBSize(FnBSize fnBSize) throws SQLException {
        fnBSizeDAO.updateFnBSize(fnBSize);
    }

    @Override
    public FnBType getFnBType(String id, int storeid) throws SQLException {
        return fnBTypeDAO.getFnBType(id, storeid);
    }

    @Override
    public FnBType getFnBTypeTrue(String id, int storeid) throws SQLException {
        return fnBTypeDAO.getFnBTypeTrue(id, storeid);
    }

    @Override
    public List<FnBType> getAllFnBType(int storeid) throws SQLException {
        return fnBTypeDAO.getAllFnBType(storeid);
    }

    @Override
    public List<FnBType> getAllFnBTypeTrue(int storeid) throws SQLException {
        return fnBTypeDAO.getAllFnBTypeTrue(storeid);
    }

    @Override
    public void addFnBType(FnBType fnBType) throws SQLException {
        fnBTypeDAO.addFnBType(fnBType);
    }

    @Override
    public void deleteFnBType(String fnBType, int storeid) throws SQLException {
        fnBTypeDAO.deleteFnBType(fnBType, storeid);
    }

    @Override
    public void updateFnBType(FnBType fnBType) throws SQLException {
        fnBTypeDAO.updateFnBType(fnBType);
    }
}
