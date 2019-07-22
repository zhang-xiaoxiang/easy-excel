package com.example.easy.excel.entity;

import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author zhangxiaoxiang
 * @since 2019-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键ID(使用Varchar也行)
     * 我这里没有使用主键自增策略,只标注为这个是主键就行了
     */
    @TableId
    private String userId;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 用户生日
     */
    private LocalDateTime birthday;

    /**
     * 性别(0表示女,1表示男)可以拓展3表示变态
     */
    private Integer sex;

    /**
     * 用户身高
     */
    private Double height;

    /**
     * 用户地址
     */
    private String address;

    /**
     * 用户积分
     */
    private Float credits;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
