package com.example.easy.excel.service;

import com.alibaba.excel.metadata.BaseRowModel;
import com.example.easy.excel.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.easy.excel.excel.ExcelWriterFactroy;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhangxiaoxiang
 * @since 2019-07-21
 */
public interface UserService extends IService<User> {
    /**
     * 导出 Excel（一个 sheet）
     * @param response
     * @return
     */
    List<User> exportUserExcel(HttpServletResponse response);

    /**
     * 导入 Excel（一个 sheet）到数据库
     * @param excel 需要导入的excel
     * @param headLineNum
     * @return
     */
    List<Object> importUserExcel(MultipartFile excel, BaseRowModel rowModel, int headLineNum);

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
