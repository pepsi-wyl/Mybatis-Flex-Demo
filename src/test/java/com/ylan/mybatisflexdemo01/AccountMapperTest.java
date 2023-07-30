package com.ylan.mybatisflexdemo01;

import com.ylan.mybatisflexdemo01.entity.Account;
import com.ylan.mybatisflexdemo01.mapper.AccountMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author by pepsi-wyl
 * @date 2023-07-30 17:09
 */

@SpringBootTest
public class AccountMapperTest {

    @Autowired
    private AccountMapper accountMapper;

    @Test
    void testInsert() {
        // 构建实体类
        Account account = Account.builder().userName("Ylan").age(18).birthday(new Date()).build();

//        int insert = accountMapper.insert(account);
        int insert = accountMapper.insert(account, true);
//        int insert = accountMapper.insertSelective(account);

        // 获取自增的ID值
        System.out.println("AccountId--->" + account.getId());

        // 断言
        Assertions.assertEquals(1, insert);
    }

    @Test
    void testInsertWithPk() {
        // 构建实体类
        Account account = Account.builder().id(6L).userName("Ylan").age(18).birthday(new Date()).build();

//        int insert = accountMapper.insertWithPk(account);
        int insert = accountMapper.insertWithPk(account, true);
//        int insert = accountMapper.insertSelectiveWithPk(account);

        // 断言
        Assertions.assertEquals(1, insert);
    }

    @Test
    void testInsertOrUpdate() {
        // 构建实体类
        Account account = Account.builder()
//                .id(6L) // Id存在则根据Id更新，没有则插入
                .userName("Ylan").age(18).birthday(new Date()).build();

//        int rows = accountMapper.insertOrUpdate(account);
        int rows = accountMapper.insertOrUpdate(account, true);
//        int rows = accountMapper.insertOrUpdateSelective(account);

        // 断言
        Assertions.assertEquals(1, rows);
    }

    @Test
    void testInsertBatch() {
        // 准备数据 以字段最多的数据为主进行拼接Sql
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(Account.builder().userName("YlanInsertBatch1").age(18).birthday(new Date()).build());
        accounts.add(Account.builder().userName("YlanInsertBatch2").age(18).birthday(new Date()).build());
        accounts.add(Account.builder().userName("YlanInsertBatch3").age(18).birthday(new Date()).build());

        int rows = accountMapper.insertBatch(accounts); // 不忽略Null值

        // 断言
        Assertions.assertEquals(3, rows);
    }

}
