package Service;

import DAO.StoreAccountDAO;
import DAO.SuperAdminDAO;
import Model.StoreAccount;
import Model.SuperAdmin;

import java.sql.SQLException;
import java.util.List;

public class SuperAdminServiceDatabase implements SuperAdminService {
    SuperAdminDAO superAdminDAO;

    @Override
    public SuperAdmin getSuperAdmin(String username) throws SQLException {
        superAdminDAO = new SuperAdminDAO();
        return superAdminDAO.getSuperAdmin(username);
    }

    @Override
    public List<SuperAdmin> getAllSuperAdmin(int offset) throws SQLException {
        superAdminDAO = new SuperAdminDAO();
        return superAdminDAO.getAllSuperAdmin(offset);
    }

    @Override
    public int getCountAllSuperAdmin() throws SQLException {
        superAdminDAO = new SuperAdminDAO();
        return superAdminDAO.getCountAllSuperAdmin();
    }

    @Override
    public void addSuperAdmin(SuperAdmin account) throws SQLException {
        superAdminDAO = new SuperAdminDAO();
        superAdminDAO.addSuperAdmin(account);
    }

    @Override
    public void deleteSuperAdmin(int id) throws SQLException {
        superAdminDAO = new SuperAdminDAO();
        superAdminDAO.deleteSuperAdmin(id);
    }

    @Override
    public void retrieveSuperAdmin(int id) throws SQLException {
        superAdminDAO = new SuperAdminDAO();
        superAdminDAO.retrieveSuperAdmin(id);
    }

    @Override
    public void updateSuperAdmin(SuperAdmin account) throws SQLException {
        superAdminDAO = new SuperAdminDAO();
        superAdminDAO.updateSuperAdmin(account);
    }
}
