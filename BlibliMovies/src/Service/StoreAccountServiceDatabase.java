package Service;

import DAO.StoreAccountDAO;
import Model.StoreAccount;

import java.sql.SQLException;

public class StoreAccountServiceDatabase implements StoreAccountService {
    StoreAccountDAO storeAccountDAO = new StoreAccountDAO();

    public StoreAccount getStoreAccount(String username) throws SQLException {
        return storeAccountDAO.getStoreAccount(username);
    }
}
