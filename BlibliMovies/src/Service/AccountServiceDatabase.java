package Service;

import Model.Account;
import DAO.AccountDAO;

import java.sql.SQLException;
import java.util.List;

public class AccountServiceDatabase implements AccountService {
    AccountDAO accountDAO = new AccountDAO();

    @Override
    public Account getAccount(String username) throws SQLException{
        return accountDAO.getAccount(username);
    }

    @Override
    public List<Account> getAllAccount() throws SQLException{
        return accountDAO.getAllAccount();
    }

    @Override
    public void addAccount(Account account) throws SQLException{
        accountDAO.addAccount(account);
    }

    @Override
    public void deleteAccount(String account) throws SQLException{
        accountDAO.deleteAccount(account);
    }

    @Override
    public void updateAccout(Account account) throws SQLException{
        accountDAO.updateAccount(account);
    }
}
