package Service;

import Model.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountService {

    Account getAccount(String username, String storename) throws SQLException;
    List<Account> getAllAccount(String storename) throws SQLException;
    void addAccount(Account account) throws SQLException;
    void deleteAccount(String username, String storename) throws SQLException;
    void updateAccout(Account account) throws SQLException;
}
