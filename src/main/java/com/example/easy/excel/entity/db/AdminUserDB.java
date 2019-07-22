package com.example.easy.excel.entity.db;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;

/**
 * AdminUserDB:管理员入库实体
 *
 * @author zhangxiaoxiang
 * @date: 2019/07/21
 */
@Data
public class AdminUserDB  extends BaseRowModel implements Serializable {

    /**
     * 姓名
     * index = 0表示姓名为第一列的excel对应到数据库的字段
     */
    @ExcelProperty(index = 0)
    private String name;

    /**
     * 密码
     */
    @ExcelProperty(index = 1)
    private String password;

    /**
     * 管理员级别
     */
    @ExcelProperty(index = 2)
    private String level;

}
