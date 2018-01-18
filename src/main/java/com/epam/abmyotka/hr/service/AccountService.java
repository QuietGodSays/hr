package com.epam.abmyotka.hr.service;

import com.epam.abmyotka.hr.dao.AccountDAO;
import com.epam.abmyotka.hr.dao.CandidateDAO;
import com.epam.abmyotka.hr.dao.DBPool;
import com.epam.abmyotka.hr.entity.Account;

import java.sql.Connection;
import java.util.List;

public class AccountService {

    public Account find(Account user) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        AccountDAO accountDAO = new AccountDAO(connection);
        List<Account> accounts = accountDAO.findAll();

        Account role = null;

        for(Account account : accounts) {
            if (account.getLogin().equals(user.getLogin()) && account.getPassword().equals(user.getPassword())) {
                role = account;
            }
        }

        pool.putConnection(connection);
        return role;
    }

    public int findAccountIdByPassword(String password) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        AccountDAO accountDAO = new AccountDAO(connection);
        int accountId = accountDAO.findAccountIdByPassword(password);
        pool.putConnection(connection);
        return accountId;
    }

    public int findAccountIdByLoginPasswordAttachment(Account account) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        AccountDAO accountDAO = new AccountDAO(connection);
        int accountId = accountDAO.findAccountIdByLoginPasswordAttachment(account);
        pool.putConnection(connection);
        return accountId;
    }

    public boolean add(Account account) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        AccountDAO accountDAO = new AccountDAO(connection);
        int countRowsAffected = accountDAO.add(account);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }

    public boolean update(Account account) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        AccountDAO accountDAO = new AccountDAO(connection);
        int countRowsAffected = accountDAO.update(account);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }

    public boolean delete(Account account) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        AccountDAO accountDAO = new AccountDAO(connection);
        int countRowsAffected = accountDAO.delete(account);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }

    public boolean checkCoincidenceByLogin(String login) {
        boolean isExist;
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        AccountDAO accountDAO = new AccountDAO(connection);
        isExist = accountDAO.checkCoincidenceByLogin(login);
        pool.putConnection(connection);
        return isExist;
    }
}
