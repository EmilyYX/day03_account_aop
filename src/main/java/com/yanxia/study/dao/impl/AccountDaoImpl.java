package com.yanxia.study.dao.impl;

import com.yanxia.study.dao.IAccountDao;
import com.yanxia.study.domain.Account;
import com.yanxia.study.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class AccountDaoImpl implements IAccountDao {

    private QueryRunner runner;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    private ConnectionUtils connectionUtils;

    @Override
    public List<Account> findAllAccount() throws SQLException {
        return runner.query(connectionUtils.getThreadConnection(),"select * from account;", new BeanListHandler<Account>(Account.class));
    }

    @Override
    public Account findAccountById(String accountId) throws SQLException {
        return runner.query(connectionUtils.getThreadConnection(),"select * from account where id = ?;", new BeanHandler<Account>(Account.class), accountId);
    }

    @Override
    public void updateAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"update account set money = ? where id=?", account.getMoney(), account.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccountById() {

    }

    @Override
    public void saveAccount(Account account) {

    }

    @Override
    public Account findAccountByName(String accountName) {
        try {
            List<Account> accountList = runner.query(connectionUtils.getThreadConnection(),"select * from account where name = ?;", new BeanListHandler<Account>(Account.class), accountName);
            if(accountList==null||accountList.isEmpty()){
                return null;
            }else if(accountList.size()>1){
                throw new Exception("结果非唯一");
            }
            return accountList.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }
}
