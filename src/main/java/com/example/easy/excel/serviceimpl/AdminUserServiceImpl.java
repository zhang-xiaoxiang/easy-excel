package com.example.easy.excel.serviceimpl;

import com.alibaba.excel.metadata.BaseRowModel;
import com.example.easy.excel.entity.AdminUser;
import com.example.easy.excel.dao.AdminUserDao;
import com.example.easy.excel.entity.User;
import com.example.easy.excel.entity.db.AdminUserDB;
import com.example.easy.excel.entity.db.UserDB;
import com.example.easy.excel.excel.ExcelUtil;
import com.example.easy.excel.service.AdminUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.easy.excel.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 管理员用户 服务实现类
 * </p>
 *
 * @author zhangxiaoxiang
 * @since 2019-07-21
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserDao, AdminUser> implements AdminUserService {
    @Autowired
    private AdminUserDao adminUserDao;

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel    文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @param sheetNo  sheet 的序号 从1开始
     * @return Excel 数据 list
     */
    @Override
    public List<Object> importAdminUserExcel(MultipartFile excel, BaseRowModel rowModel, int sheetNo, int headLineNum) {
        if (excel == null) {
            log.error("请传入正确的excel格式");
            return null;
        }
        List<Object> list = ExcelUtil.readExcel(excel, new AdminUserDB(), sheetNo);
        AdminUser adminUser = new AdminUser();
        //注意去除表头(以行作为集合的对象),比如第一行是表头,第二行才是数据那么i=1开始,依次类推
        //这里可以写业务逻辑和一些判断,比如年龄<120,性别只能是1或者2,其他不合法的不让导入数据库
        for (int i = 1; i < list.size(); i++) {
            //这里需要转一下(把表格数据的列从左往右0开始依次获取值,注意一一对应)
            List<String> list1 = (List<String>) list.get(i);
            adminUser.setName(list1.get(0));
            adminUser.setPassword(list1.get(1));
            adminUser.setLevel(list1.get(2));
            adminUser.setCreateTime(LocalDateTime.now());
            //没有处理异常,未来看起来简单,你知道怎么操作就像
            try {
                adminUserDao.insert(adminUser);
            } catch (Exception e) {
                e.printStackTrace();
                //比如主键重复等错误
                log.warn("数据库插入出错" + e.getMessage());
                return null;
            }
        }
        return ExcelUtil.readExcel(excel, new AdminUserDB(), sheetNo, headLineNum);
    }

    /**
     * 导入多sheet的 excel
     *
     * @param excel
     * @param rowModel
     * @param sheetNo
     * @param headLineNum
     * @return
     */
    @Override
    public List<Object> inportManySheet(MultipartFile excel, BaseRowModel rowModel, int sheetNo, int headLineNum) {

        if (excel == null) {
            log.error("请传入正确的excel格式");
            return null;
        }
        List<Object> list = ExcelUtil.readExcel(excel, new AdminUserDB(), sheetNo, headLineNum);
        AdminUser adminUser = new AdminUser();
        try {
            for (int i = 0; i < list.size(); i++) {
                //刻意使用主键自增,这里就不需要主键(或者ID编号了)
                AdminUserDB adminUserDB = (AdminUserDB) list.get(i);
                adminUser.setName(adminUserDB.getName());
                adminUser.setPassword(adminUserDB.getPassword());
                adminUser.setLevel(adminUserDB.getLevel());
                adminUser.setCreateTime(LocalDateTime.now());
                adminUserDao.insert(adminUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("写入excel到数据库失败!");
            return null;
        }

        return ExcelUtil.readExcel(excel, new AdminUserDB(), headLineNum);
    }
}
