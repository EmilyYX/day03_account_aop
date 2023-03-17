package com.yanxia.study.test;


import com.yanxia.study.domain.Account;
import com.yanxia.study.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

/**
 * 使用Junit单元测试配置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:bean.xml")
public class AccountServiceTest {

    @Autowired
    private IAccountService as;
    @Test
    public void testFindAll() {
        List<Account> accountList = as.findAllAccount();
        for(Account account: accountList){
            System.out.println(account);
        }
    }

    @Test
    public void testTransfer(){
        as.transfer("aaa","bbb", 100F);
    }

}
