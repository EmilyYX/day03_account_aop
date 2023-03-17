package com.yanxia.study.service;

import com.yanxia.study.domain.Account;

import java.sql.SQLException;
import java.util.List;

/**
 * 账户的业务层接口
 */
public interface IAccountService {
    /**
     * 查询所有账号
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 用id查询单个账号
     * @return
     */
    Account findAccountById(String accountId) throws SQLException;

    /**
     * 更新一个账号
     */
    void updateAccount(Account account) throws SQLException;

    /**
     * 删除一个账号
     */
    void deleteAccountById();

    /**
     * 保存一个账号
     */
    void saveAccount(Account account);

    /**
     * 转账
     * @param sourceName 转出账户名称
     * @param targetName 转入账户名称
     * @param money 转账金额
     */
    void transfer(String sourceName, String targetName, Float money);

}
