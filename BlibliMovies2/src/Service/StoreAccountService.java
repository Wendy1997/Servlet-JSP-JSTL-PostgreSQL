package Service;

import Model.Account;
import Model.StoreAccount;

import java.sql.SQLException;
import java.util.List;

public interface StoreAccountService {
    StoreAccount getStoreAccount(String username) throws SQLException;
    List<StoreAccount> getAllStoreAccount(int offset) throws SQLException;
    int getCountAllStoreAccount() throws SQLException;
    void addStoreAccount(StoreAccount account) throws SQLException;
    void deleteStoreAccount(int storeid) throws SQLException;
    void retrieveStoreAccount(int storeid) throws SQLException;
    void updateStoreAccount(StoreAccount account) throws SQLException;
}
