package Service;

import DAO.FnBDAO;
import Model.FnB;
import Model.FnB;
import Model.FnBSize;
import Model.FnBType;

import java.sql.SQLException;
import java.util.List;

public interface FnBService {

    FnB getFnB(String id, String storename) throws SQLException;
    List<FnB> getAllFnB(String storename) throws SQLException;
    void addFnB(FnB fnb) throws SQLException;
    void deleteFnB(String id, String storename) throws SQLException;
    void updateFnB(FnB fnb) throws SQLException;

    FnBSize getFnBSize(String id, String storename) throws SQLException;
    List<FnBSize> getAllFnBSize(String storename) throws SQLException;
    void addFnBSize(FnBSize fnBSize) throws SQLException;
    void deleteFnBSize(String fnBSize, String storename) throws SQLException;
    void updateFnBSize(FnBSize fnBSize) throws SQLException;

    FnBType getFnBType(String id, String storename) throws SQLException;
    List<FnBType> getAllFnBType(String storename) throws SQLException;
    void addFnBType(FnBType fnBType) throws SQLException;
    void deleteFnBType(String fnBType, String storename) throws SQLException;
    void updateFnBType(FnBType fnBType) throws SQLException;

}
