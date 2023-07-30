package com.ylan.mybatisflexdemo01;

import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.ylan.mybatisflexdemo01.entity.Account;
import com.ylan.mybatisflexdemo01.mapper.AccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.ylan.mybatisflexdemo01.entity.table.AccountTableDef.ACCOUNT;

@SpringBootTest
class MybatisFlexDemo01ApplicationTests {

    @Autowired
    private AccountMapper accountMapper;

    @Test
    void selectAll() {
        List<Account> accounts = accountMapper.selectAll();
        accounts.forEach(System.out::println);
    }

    @Test
    void selectOne() {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(ACCOUNT.ALL_COLUMNS)
                .from(ACCOUNT)
                .where(ACCOUNT.AGE.eq(18));
        Account account = accountMapper.selectOneByQuery(queryWrapper);
        System.out.println(account);
    }

    @Test
    void tableDef() {
        System.out.println(ACCOUNT.getSchema());
        System.out.println(ACCOUNT.getTableName());
        System.out.println(ACCOUNT.ALL_COLUMNS);
        System.out.println(Arrays.toString(ACCOUNT.DEFAULT_COLUMNS));
        ACCOUNT.AGE.getName();
    }

    @Test
    void baseMapperDemo() {

        Account account = Account.builder().age(18).build();
        accountMapper.insert(account);          // 不忽略Null值               createTime = null
        accountMapper.insert(account, true); // 忽略Null值 使用数据库默认的值 createTime = now()

        // 1.byId   where id = xxx
        // 2.byMap
        //    HashMap<String, Object> map = new HashMap<>();
        //    map.put("id", 1);
        // 3.byQueryCondition
        //    QueryCondition eq = ACCOUNT.ID.eq(1);
        // 4.byQueryWrapper
        //    QueryWrapper where = QueryWrapper.create().where(ACCOUNT.ID.eq(1));

        // 查询VO AccountVO
    }


}