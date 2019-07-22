package com.example.easy.excel.service;

import com.alibaba.excel.metadata.BaseRowModel;
import com.example.easy.excel.entity.AdminUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 管理员用户 服务类
 * </p>
 *
 * @author zhangxiaoxiang
 * @since 2019-07-21
 */
public interface AdminUserService extends IService<AdminUser> {

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel    文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @param sheetNo  sheet 的序号 从1开始
     * @return Excel 数据 list
     */
    List<Object> importAdminUserExcel(MultipartFile excel, BaseRowModel rowModel, int sheetNo, int headLineNum);
    /**
     * 导入多sheet的 excel
     * @param excel
     * @param rowModel
     * @param sheetNo
     * @param headLineNum
     * @return
     */
    List<Object>  inportManySheet(MultipartFile excel, BaseRowModel rowModel, int sheetNo, int headLineNum);
}
