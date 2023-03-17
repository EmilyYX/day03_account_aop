package com.yanxia.study.service.impl;

import com.yanxia.study.dao.IAccountDao;
import com.yanxia.study.domain.Account;
import com.yanxia.study.service.IAccountService;
import com.yanxia.study.utils.TransactionManager;

import java.sql.SQLException;
import java.util.List;

public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    private TransactionManager transactionManager;

    @Override
    public List<Account> findAllAccount() {
        List<Account> accountList = null;
        try {
            accountList = accountDao.findAllAccount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    @Override
    public Account findAccountById(String accountId) throws SQLException {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public void updateAccount(Account account) throws SQLException {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccountById() {
        accountDao.deleteAccountById();
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    /**
     * 转账
     *
     * @param sourceName 转出账户名称
     * @param targetName 转入账户名称
     * @param money      转账金额
     */
    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        //根据名称查询转出账户
        //根据名称查询转出账户
        //转出账户减钱
        //转入账户加钱
        //更新转出账户，更新转入账户
        try {
            transactionManager.beginTransation();
            Account sourceAccount = accountDao.findAccountByName(sourceName);
            Account targetAccount = accountDao.findAccountByName(targetName);
            sourceAccount.setMoney(sourceAccount.getMoney() - money);
            targetAccount.setMoney(targetAccount.getMoney() + money);
            accountDao.updateAccount(sourceAccount);
            int i = 1/0;
            accountDao.updateAccount(targetAccount);
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollback();
            throw new RuntimeException(e);
        } finally {
            transactionManager.release();
        }
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
