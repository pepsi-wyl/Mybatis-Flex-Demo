package com.ylan.mybatisflexdemo01;

import com.mybatisflex.core.query.QueryWrapper;
import com.ylan.mybatisflexdemo01.entity.Account;
import com.ylan.mybatisflexdemo01.mapper.AccountMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import static com.ylan.mybatisflexdemo01.entity.table.AccountTableDef.ACCOUNT;

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

    @Test
    void testDelete() {
        int rows = 0;

        rows += accountMapper.deleteById(3);

        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 4);
        rows += accountMapper.deleteByMap(map);

        rows += accountMapper.deleteByCondition(ACCOUNT.ID.eq(5));

        rows += accountMapper.deleteByQuery(QueryWrapper.create().where(ACCOUNT.ID.eq(6)));

        Assertions.assertEquals(rows, 4);
    }

    @Test
    void testDeleteBatch() {
        int rows = 0;

        rows += accountMapper.deleteBatchByIds(Arrays.asList(8, 9, 10));

        Assertions.assertEquals(rows, 3);
    }

    @Test
    void testDeleteAll() {
        // 禁止全表删除，直接抛出异常
        Assertions.assertThrows(Exception.class, () -> {
            accountMapper.deleteByQuery(QueryWrapper.create());
        });
    }

    @Test
    void testUpdate() {
        // 根据ID删除
        Account account = Account.builder().id(1L).userName("Ylan").build();

        int row = accountMapper.update(account);           // 忽略Null值 默认
//        int row = accountMapper.update(account, false);  // 不忽略Null值

        Assertions.assertEquals(1, row);
    }

    @Test
    void testUpdateCondition() {
        Account account = Account.builder().age(200).build();  // 需要修改的属性

        // 忽略Null值 默认
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_name", "张三");
        accountMapper.updateByMap(account, map);
        accountMapper.updateByCondition(account, ACCOUNT.USER_NAME.eq("张三"));
        accountMapper.updateByQuery(account, QueryWrapper.create().where(ACCOUNT.USER_NAME.eq("张三")));

        // 不忽略Null值
        accountMapper.updateByMap(account, false, map);
        accountMapper.updateByCondition(account, false, ACCOUNT.USER_NAME.eq("张三"));
        accountMapper.updateByQuery(account, false, QueryWrapper.create().where(ACCOUNT.USER_NAME.eq("张三")));
    }

    @Test
    void testUpdateNumber() {
        // set age = age + n
        accountMapper.updateNumberAddByQuery(ACCOUNT.AGE, 1, QueryWrapper.create().where(ACCOUNT.USER_NAME.eq("张三")));
    }

    @Test
    void testUpdateAll() {
        // 需要修改的属性
        Account account = Account.builder().age(100).build();

        // 禁止全表更新，直接抛出异常
        Assertions.assertThrows(Exception.class, () -> {
            accountMapper.updateByQuery(account, QueryWrapper.create());
        });
    }

}