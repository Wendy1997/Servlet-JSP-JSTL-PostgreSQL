package Service;

import DAO.StoreAccountDAO;
import Model.Account;
import Model.StoreAccount;

import java.sql.SQLException;
import java.util.List;

public class StoreAccountServiceDatabase implements StoreAccountService {
    StoreAccountDAO storeAccountDAO = new StoreAccountDAO();

    @Override
    public StoreAccount getStoreAccount(String username) throws SQLException {
        return storeAccountDAO.getStoreAccount(username);
    }

    @Override
    public List<StoreAccount> getAllStoreAccount(int offset) throws SQLException {
        return storeAccountDAO.getAllStoreAccount(offset);
    }

    @Override
    public int getCountAllStoreAccount() throws SQLException {
        return storeAccountDAO.getCountAllStoreAccount();
    }

    @Override
    public void addStoreAccount(StoreAccount account) throws SQLException {
        storeAccountDAO.addStoreAccount(account);
    }

    @Override
    public void deleteStoreAccount(int storeid) throws SQLException {
        storeAccountDAO.deleteStoreAccount(storeid);
    }

    @Override
    public void retrieveStoreAccount(int storeid) throws SQLException {
        storeAccountDAO.retrieveStoreAccount(storeid);
    }

    @Override
    public void updateStoreAccount(StoreAccount account) throws SQLException {
        storeAccountDAO.updateStoreAccount(account);
    }
}
