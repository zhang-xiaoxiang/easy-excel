package com.example.easy.excel.entity.db;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;

/**
 * UserDB:数据库导入(导入的列可以自定义,也可以全部导入)
 * 一般能把所有表格数据都导入,也可以选择性的
 * <p>
 * 数据库需要的数据写在这个实体类,其他需要处理字段属性在业务层写就可以了
 * <p>
 * 用户数据库的DDL
 * CREATE TABLE `user` (
 * `user_id` varbinary(16) NOT NULL COMMENT '用户主键ID(使用Varchar也行)',
 * `name` varchar(255) DEFAULT NULL COMMENT '用户姓名',
 * `age` int(11) DEFAULT NULL COMMENT '用户年龄',
 * `birthday` date DEFAULT NULL COMMENT '用户生日',
 * `sex` int(2) DEFAULT NULL COMMENT '性别(0表示女,1表示男)可以拓展3表示变态',
 * `height` double(8,2) DEFAULT NULL COMMENT '用户身高',
 * `address` varchar(255) DEFAULT NULL COMMENT '用户地址',
 * `credits` float(8,2) DEFAULT NULL COMMENT '用户积分',-----------比如这个字段数据库有,但是Excel没有,那么就不需要写在这个类
 * `create_time` datetime DEFAULT NULL COMMENT '创建时间',---------比如这个字段数据库有,但是Excel没有,那么就不需要写在这个类
 * PRIMARY KEY (`user_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
 *
 * @author zhangxiaoxiang
 * @date: 2019/07/21
 */
@Data
public class UserDB extends BaseRowModel implements Serializable {

    //说明:这个是excel导入到数据库的实体,所以字段属性是数据库的下划线的形式



    /**
     * 用户编号 数据库列字段,一一对应就行
     * index = 0 表示表格第一列,下面依次类推
     */
    @ExcelProperty(index = 0)
    private String user_id;
    /**
     * 用户姓名
     */
    @ExcelProperty(index = 1)
    private String name;
    /**
     * 用户年龄
     */
    @ExcelProperty(index = 2)
    private String age;
    /**
     * 用户生日
     */
    @ExcelProperty(index = 3)
    private String birthday;
    /**
     * 性别(0表示女,1表示男)可以拓展3表示变态
     */
    @ExcelProperty(index = 4)
    private String sex;
    /**
     * 用户身高
     */
    @ExcelProperty(index = 5)
    private String height;
    /**
     * 用户地址
     */
    @ExcelProperty(index = 6)
    private String address;

    //如果还需要导入列的信息在这里继续写就行了,我这个示例是把表格所有的字段都导入的数据库
    // 如果有的表格的列不需要导入,那么就直接把上面写的属性删掉一些即可


}
