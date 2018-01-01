package Service;

import DAO.AccountRoleDAO;
import Model.Account;
import DAO.AccountDAO;
import Model.AccountRole;

import java.sql.SQLException;
import java.util.List;

public class AccountServiceDatabase implements AccountService {
    AccountDAO accountDAO;
    AccountRoleDAO accountRoleDAO;

    @Override
    public Account getAccount(String username, int storeid) throws SQLException{
        accountDAO = new AccountDAO();
        return accountDAO.getAccount(username, storeid);
    }

    @Override
    public Account getAccountTrue(String username, int storeid) throws SQLException {
        accountDAO = new AccountDAO();
        return accountDAO.getAccountTrue(username, storeid);
    }

    @Override
    public List<Account> getAllAccount(int storeid, int offset) throws SQLException{
        accountDAO = new AccountDAO();
        return accountDAO.getAllAccount(storeid, offset);
    }

    @Override
    public int getCountAllAccount(int storeid) throws SQLException {
        accountDAO = new AccountDAO();
        return accountDAO.getCountAllAccount(storeid);
    }

    @Override
    public void addAccount(Account account) throws SQLException{
        accountDAO = new AccountDAO();
        accountDAO.addAccount(account);
    }

    @Override
    public void deleteAccount(String username, int storeid) throws SQLException{
        accountDAO = new AccountDAO();
        accountDAO.deleteAccount(username, storeid);
    }

    @Override
    public void retrieveAccount(String username, int storeid) throws SQLException {
        accountDAO = new AccountDAO();
        accountDAO.retrieveAccount(username, storeid);
    }

    @Override
    public void updateAccout(Account account) throws SQLException{
        accountDAO = new AccountDAO();
        accountDAO.updateAccount(account);
    }

    @Override
    public AccountRole getAccountRole(int id, int storeid) throws SQLException {
        accountRoleDAO =  new AccountRoleDAO();
        return accountRoleDAO.getAccountRole(id, storeid);
    }

    @Override
    public List<AccountRole> getAllAccountRole(int storeid) throws SQLException {
        accountRoleDAO =  new AccountRoleDAO();
        return accountRoleDAO.getAllAccountRole(storeid);
    }

    @Override
    public List<AccountRole> getAllAccountRoleTrue(int storeid) throws SQLException {
        accountRoleDAO =  new AccountRoleDAO();
        return accountRoleDAO.getAllAccountRoleTrue(storeid);
    }

    @Override
    public void addAccountRole(AccountRole accountRole) throws SQLException {
        accountRoleDAO =  new AccountRoleDAO();
        accountRoleDAO.addAccountRole(accountRole);
    }
}
