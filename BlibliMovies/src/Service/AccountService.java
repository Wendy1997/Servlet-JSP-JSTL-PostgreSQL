package Service;

import Model.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountService {

    Account getAccount(String username) throws SQLException;
    List<Account> getAllAccount() throws SQLException;
    void addAccount(Account account) throws SQLException;
    void deleteAccount(String account) throws SQLException;
    void updateAccout(Account account) throws SQLException;
}
