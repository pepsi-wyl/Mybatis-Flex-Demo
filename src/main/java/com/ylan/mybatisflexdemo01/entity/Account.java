package com.ylan.mybatisflexdemo01.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Ylan
 * @date 2023-07-29 16:55
 */
@Data
@Builder
@Table("flex_account")
public class Account {

    @Id(keyType = KeyType.Auto)
    private Long id;

    private String userName;
    private Integer gender;
    private Integer age;
    private Date birthday;

}