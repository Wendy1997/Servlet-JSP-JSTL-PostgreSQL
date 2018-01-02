package Service;

import Model.SuperAdmin;

import java.sql.SQLException;
import java.util.List;

public interface SuperAdminService {
    SuperAdmin getSuperAdmin(String username) throws SQLException;
    List<SuperAdmin> getAllSuperAdmin(int offset) throws SQLException;
    int getCountAllSuperAdmin() throws SQLException;
    void addSuperAdmin(SuperAdmin account) throws SQLException;
    void deleteSuperAdmin(int id) throws SQLException;
    void retrieveSuperAdmin(int id) throws SQLException;
    void updateSuperAdmin(SuperAdmin account) throws SQLException;
}
