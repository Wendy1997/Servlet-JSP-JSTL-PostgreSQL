package Service;

import Model.Account;
import Model.AccountRole;

import java.sql.SQLException;
import java.util.List;

public interface AccountService {

    Account getAccount(String username, int storeid) throws SQLException;
    List<Account> getAllAccount(int storeid) throws SQLException;
    void addAccount(Account account) throws SQLException;
    void deleteAccount(String username, int storeid) throws SQLException;
    void updateAccout(Account account) throws SQLException;

    AccountRole getAccountRole(String id, int storeid) throws SQLException;
    List<AccountRole> getAllAccountRole(int storeid) throws SQLException;
    void addAccountRole(AccountRole accountRole) throws SQLException;
    void deleteAccountRole(String accountRole, int storeid) throws SQLException;
    void updateAccountRole(AccountRole accountRole) throws SQLException;
}
