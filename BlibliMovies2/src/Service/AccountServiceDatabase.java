package Service;

import DAO.AccountRoleDAO;
import Model.Account;
import DAO.AccountDAO;
import Model.AccountRole;

import java.sql.SQLException;
import java.util.List;

public class AccountServiceDatabase implements AccountService {
    AccountDAO accountDAO = new AccountDAO();
    AccountRoleDAO accountRoleDAO =  new AccountRoleDAO();

    @Override
    public Account getAccount(String username, String storename) throws SQLException{
        return accountDAO.getAccount(username, storename);
    }

    @Override
    public List<Account> getAllAccount(String storename) throws SQLException{
        return accountDAO.getAllAccount(storename);
    }

    @Override
    public void addAccount(Account account) throws SQLException{
        accountDAO.addAccount(account);
    }

    @Override
    public void deleteAccount(String username, String storename) throws SQLException{
        accountDAO.deleteAccount(username, storename);
    }

    @Override
    public void updateAccout(Account account) throws SQLException{
        accountDAO.updateAccount(account);
    }

    @Override
    public AccountRole getAccountRole(String id, String storename) throws SQLException {
        return accountRoleDAO.getAccountRole(id, storename);
    }

    @Override
    public List<AccountRole> getAllAccountRole(String storename) throws SQLException {
        return accountRoleDAO.getAllAccountRole(storename);
    }

    @Override
    public void addAccountRole(AccountRole accountRole) throws SQLException {
        accountRoleDAO.addAccountRole(accountRole);
    }

    @Override
    public void deleteAccountRole(String accountRole, String storename) throws SQLException {
        accountRoleDAO.deleteAccountRole(accountRole, storename);
    }

    @Override
    public void updateAccountRole(AccountRole accountRole) throws SQLException {
        accountRoleDAO.updateAccountRole(accountRole);
    }
}
