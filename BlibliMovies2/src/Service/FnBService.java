package Service;

import DAO.FnBDAO;
import Model.FnB;
import Model.FnB;
import Model.FnBSize;
import Model.FnBType;

import java.sql.SQLException;
import java.util.List;

public interface FnBService {

    FnB getFnB(String id, int storeid) throws SQLException;
    FnB getFnBTrue(String id, int storeid) throws SQLException;
    List<FnB> getAllFnB(int storeid, int offset) throws SQLException;
    int getCountAllFnB(int storeid) throws SQLException;
    List<FnB> getAllFnBTrue(int storeid) throws SQLException;
    void addFnB(FnB fnb) throws SQLException;
    void deleteFnB(String id, int storeid) throws SQLException;
    void retrieveFnB(String id, int storeid) throws SQLException;
    void updateFnB(FnB fnb) throws SQLException;

    FnBSize getFnBSize(String id, int storeid) throws SQLException;
    FnBSize getFnBSizeTrue(String id, int storeid) throws SQLException;
    List<FnBSize> getAllFnBSize(int storeid, int offset) throws SQLException;
    List<FnBSize> getShowAllFnBSize(int storeid) throws SQLException;
    int getCountAllFnBSize(int storeid) throws SQLException;
    List<FnBSize> getAllFnBSizeTrue(int storeid) throws SQLException;
    void addFnBSize(FnBSize fnBSize) throws SQLException;
    void deleteFnBSize(String fnBSize, int storeid) throws SQLException;
    void retrieveFnBSize(String fnBSize, int storeid) throws SQLException;
    void updateFnBSize(FnBSize fnBSize) throws SQLException;

    FnBType getFnBType(String id, int storeid) throws SQLException;
    FnBType getFnBTypeTrue(String id, int storeid) throws SQLException;
    List<FnBType> getAllFnBType(int storeid, int offset) throws SQLException;
    List<FnBType> getShowAllFnBType(int storeid) throws SQLException;
    int getCountAllFnBType(int storeid) throws SQLException;
    List<FnBType> getAllFnBTypeTrue(int storeid) throws SQLException;
    void addFnBType(FnBType fnBType) throws SQLException;
    void deleteFnBType(String fnBType, int storeid) throws SQLException;
    void retrieveFnBType(String fnBType, int storeid) throws SQLException;
    void updateFnBType(FnBType fnBType) throws SQLException;

}
