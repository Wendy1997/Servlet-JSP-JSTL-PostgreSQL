package Service;

import Model.StoreAccount;

import java.sql.SQLException;

public interface StoreAccountService {
    StoreAccount getStoreAccount(String username) throws SQLException;
}
