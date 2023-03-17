package com.yanxia.study.dao;

import com.yanxia.study.domain.Account;

import java.sql.SQLException;
import java.util.List;

/**
 * 账户的持久层接口
 */

public interface IAccountDao {
    /**
     * 查询所有账号
     * @return
     */
    List<Account> findAllAccount() throws SQLException;

    /**
     * 用id查询单个账号
     * @return
     */
    Account findAccountById(String accountId) throws SQLException;

    /**
     * 更新一个账号
     */
    void updateAccount(Account account);

    /**
     * 删除一个账号
     */
    void deleteAccountById();

    /**
     * 保存一个账号
     */
    void saveAccount(Account account);

    Account findAccountByName(String accountName);
}
