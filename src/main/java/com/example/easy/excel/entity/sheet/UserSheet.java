package com.example.easy.excel.entity.sheet;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * UserSheet:导出到桌面Excel(自定义或者全部导出)
 *
 * @author zhangxiaoxiang
 * @date: 2019/07/21
 */
@Data
public class UserSheet extends BaseRowModel implements Serializable {

    //说明:这个是数据库导出到桌面Excel的实体,所以采用的使用驼峰命名
    // value值对应表头(建议使用数据库注释)


    /**
     * 用户编号 实体列字段,一一对应就行
     * index = 0 表示数据库第一列的字段,转换为excel的第一列,下面依次类推
     */
    @ExcelProperty(value = "用户编号", index = 0)
    private String userId;
    /**
     * 用户姓名
     */
    @ExcelProperty(value = "用户姓名", index = 1)
    private String name;
    /**
     * 用户年龄
     */
    @ExcelProperty(value = "用户年龄", index = 2)
    private Integer age;
    /**
     * 用户生日
     */
    @ExcelProperty(value = "用户生日", index = 3)
    private LocalDateTime birthday;
    /**
     * 性别(0表示女,1表示男)可以拓展3表示变态
     */
    @ExcelProperty(value = "性别", index = 4)
    private Integer sex;
    /**
     * 用户身高
     */
    @ExcelProperty(value = "用户身高", index = 5)
    private Double height;
    /**
     * 用户地址
     */
    @ExcelProperty(value = "用户地址", index = 6)
    private String address;

    /**
     * 用户积分
     */
    @ExcelProperty(value = "用户积分", index = 7)
    private Float credits;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间", index = 8)
    private LocalDateTime createTime;





}
