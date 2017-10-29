package Service;

import Model.Account;
import DAO.AccountDAO;

import java.sql.SQLException;
import java.util.List;

public class AccountServiceDatabase implements AccountService {
    AccountDAO accountDAO = new AccountDAO();

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
}
