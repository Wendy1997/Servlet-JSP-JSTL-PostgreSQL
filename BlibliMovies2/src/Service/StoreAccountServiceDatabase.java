package Service;

import DAO.StoreAccountDAO;
import Model.StoreAccount;

import java.sql.SQLException;

public class StoreAccountServiceDatabase implements StoreAccountService {
    StoreAccountDAO storeAccountDAO = new StoreAccountDAO();

    @Override
    public StoreAccount getStoreAccount(String username) throws SQLException {
        return storeAccountDAO.getStoreAccount(username);
    }
}
