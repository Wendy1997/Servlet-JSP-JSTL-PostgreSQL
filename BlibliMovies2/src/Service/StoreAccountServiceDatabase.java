package Service;

import DAO.StoreAccountDAO;
import Model.Account;
import Model.StoreAccount;

import java.sql.SQLException;
import java.util.List;

public class StoreAccountServiceDatabase implements StoreAccountService {
    StoreAccountDAO storeAccountDAO;

    @Override
    public StoreAccount getStoreAccount(String username) throws SQLException {
        storeAccountDAO = new StoreAccountDAO();
        return storeAccountDAO.getStoreAccount(username);
    }

    @Override
    public StoreAccount getStoreAccountTrue(String username) throws SQLException {
        storeAccountDAO = new StoreAccountDAO();
        return storeAccountDAO.getStoreAccountTrue(username);
    }

    @Override
    public List<StoreAccount> getAllStoreAccount(int offset) throws SQLException {
        storeAccountDAO = new StoreAccountDAO();
        return storeAccountDAO.getAllStoreAccount(offset);
    }

    @Override
    public int getCountAllStoreAccount() throws SQLException {
        storeAccountDAO = new StoreAccountDAO();
        return storeAccountDAO.getCountAllStoreAccount();
    }

    @Override
    public void addStoreAccount(StoreAccount account) throws SQLException {
        storeAccountDAO = new StoreAccountDAO();
        storeAccountDAO.addStoreAccount(account);
    }

    @Override
    public void deleteStoreAccount(int storeid) throws SQLException {
        storeAccountDAO = new StoreAccountDAO();
        storeAccountDAO.deleteStoreAccount(storeid);
    }

    @Override
    public void retrieveStoreAccount(int storeid) throws SQLException {
        storeAccountDAO = new StoreAccountDAO();
        storeAccountDAO.retrieveStoreAccount(storeid);
    }

    @Override
    public void updateStoreAccount(StoreAccount account) throws SQLException {
        storeAccountDAO = new StoreAccountDAO();
        storeAccountDAO.updateStoreAccount(account);
    }

    @Override
    public void updateStoreAccountWithoutPass(StoreAccount account) throws SQLException {
        storeAccountDAO = new StoreAccountDAO();
        storeAccountDAO.updateStoreAccountWithoutPass(account);
    }
}
