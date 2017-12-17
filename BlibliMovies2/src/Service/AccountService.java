package Service;

import Model.Account;
import Model.AccountRole;

import java.sql.SQLException;
import java.util.List;

public interface AccountService {

    Account getAccount(String username, String storename) throws SQLException;
    List<Account> getAllAccount(String storename) throws SQLException;
    void addAccount(Account account) throws SQLException;
    void deleteAccount(String username, String storename) throws SQLException;
    void updateAccout(Account account) throws SQLException;

    AccountRole getAccountRole(String id, String storename) throws SQLException;
    List<AccountRole> getAllAccountRole(String storename) throws SQLException;
    void addAccountRole(AccountRole accountRole) throws SQLException;
    void deleteAccountRole(String accountRole, String storename) throws SQLException;
    void updateAccountRole(AccountRole accountRole) throws SQLException;
}
