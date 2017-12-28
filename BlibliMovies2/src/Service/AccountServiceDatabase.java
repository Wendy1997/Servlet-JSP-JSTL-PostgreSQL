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
    public Account getAccount(String username, int storeid) throws SQLException{
        return accountDAO.getAccount(username, storeid);
    }

    @Override
    public List<Account> getAllAccount(int storeid) throws SQLException{
        return accountDAO.getAllAccount(storeid);
    }

    @Override
    public void addAccount(Account account) throws SQLException{
        accountDAO.addAccount(account);
    }

    @Override
    public void deleteAccount(String username, int storeid) throws SQLException{
        accountDAO.deleteAccount(username, storeid);
    }

    @Override
    public void updateAccout(Account account) throws SQLException{
        accountDAO.updateAccount(account);
    }

    @Override
    public AccountRole getAccountRole(String id, int storeid) throws SQLException {
        return accountRoleDAO.getAccountRole(id, storeid);
    }

    @Override
    public List<AccountRole> getAllAccountRole(int storeid) throws SQLException {
        return accountRoleDAO.getAllAccountRole(storeid);
    }

    @Override
    public void addAccountRole(AccountRole accountRole) throws SQLException {
        accountRoleDAO.addAccountRole(accountRole);
    }

    @Override
    public void deleteAccountRole(String accountRole, int storeid) throws SQLException {
        accountRoleDAO.deleteAccountRole(accountRole, storeid);
    }

    @Override
    public void updateAccountRole(AccountRole accountRole) throws SQLException {
        accountRoleDAO.updateAccountRole(accountRole);
    }
}
