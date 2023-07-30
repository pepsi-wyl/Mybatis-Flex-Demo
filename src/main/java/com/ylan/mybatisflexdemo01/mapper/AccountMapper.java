package com.ylan.mybatisflexdemo01.mapper;

import com.mybatisflex.core.BaseMapper;
import com.ylan.mybatisflexdemo01.entity.Account;
import org.springframework.stereotype.Repository;

/**
 * @author by pepsi-wyl
 * @date 2023-07-29 16:55
 */

@Repository("accountMapper")
public interface AccountMapper extends BaseMapper<Account> {

}