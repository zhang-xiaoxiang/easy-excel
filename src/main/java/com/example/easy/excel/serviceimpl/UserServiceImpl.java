package com.example.easy.excel.serviceimpl;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.easy.excel.entity.User;
import com.example.easy.excel.dao.UserDao;
import com.example.easy.excel.entity.db.UserDB;
import com.example.easy.excel.entity.sheet.UserSheet;
import com.example.easy.excel.excel.ExcelUtil;
import com.example.easy.excel.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.easy.excel.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.org.apache.bcel.internal.generic.LCONST;
import org.omg.PortableInterceptor.LOCATION_FORWARD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangxiaoxiang
 * @since 2019-07-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 导出 Excel（一个 sheet）
     *
     * @param response
     * @return
     */
    @Override
    public List<User> exportUserExcel(HttpServletResponse response) {
        //mybatis plus条件构造器,如果不需要条件,那么直接全查询
        QueryWrapper queryWrapper = new QueryWrapper();
        List<User> userList = userDao.selectList(queryWrapper);
        List<UserSheet> list = new ArrayList<>();
        UserSheet userSheet = null;
        for (int i = 0; i < userList.size(); i++) {
            userSheet = new UserSheet();
            //获取数据库的用户集合
            User user = userList.get(i);
            //对集合依次写入表格中
            userSheet.setUserId(user.getUserId());
            userSheet.setName(user.getName());
            userSheet.setAge(user.getAge());
            userSheet.setBirthday(user.getBirthday());
            userSheet.setSex(user.getSex());
            userSheet.setHeight(user.getHeight());
            userSheet.setAddress(user.getAddress());
            //比如这里可以对积否则创建时间分不导出,不赋值就行了(UserSheet如果写了,那就要和它保持一致即可)
            userSheet.setCredits(user.getCredits());
            userSheet.setCreateTime(user.getCreateTime());
            list.add(userSheet);
        }
        //文件名称
        String fileName = "userExport";
        //表格sheet名称(用过excel应该知道什么是这个名称)
        String sheetName = "user";
        ExcelUtil.writeExcel(response, list, fileName, sheetName, new UserSheet());
        return null;
    }


    /**
     * 导入 Excel（一个 sheet）到数据库
     * 这里可以处理很多业务逻辑,比如传入的id不能重复,或者把插入失败的数据返回给客户端
     *
     * @param excel       需要导入的excel
     * @param headLineNum
     * @return
     */
    @Override
    public List<Object> importUserExcel(MultipartFile excel, BaseRowModel rowModel, int headLineNum) {
        if (excel == null) {
            log.error("请传入正确的excel格式");
            return null;
        }
        List<Object> list = ExcelUtil.readExcel(excel, rowModel, headLineNum);
        User user = new User();
        for (int i = 0; i < list.size(); i++) {
            UserDB userDB = (UserDB) list.get(i);
            user.setUserId(userDB.getUser_id());
            user.setName(userDB.getName());
            user.setAge(Integer.valueOf(userDB.getAge()));
            String birthday = userDB.getBirthday();
            LocalDateTime b = DateUtil.getLocalDateTime(birthday);
            user.setBirthday(b);
            user.setSex(Integer.valueOf(userDB.getSex()));
            user.setHeight(Double.valueOf(userDB.getHeight()));
            user.setAddress(userDB.getAddress());
            //    可以写很多处理后的数据
            user.setCredits(10.8f);
            user.setCreateTime(LocalDateTime.now());
            userDao.insert(user);
        }
        return ExcelUtil.readExcel(excel, new UserDB(), headLineNum);
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
        List<Object> list = ExcelUtil.readExcel(excel, new UserDB(), sheetNo, headLineNum);
        User user = new User();
        try {
            for (int i = 0; i < list.size(); i++) {
                UserDB userDB = (UserDB) list.get(i);
                user.setUserId(userDB.getUser_id());
                user.setName(userDB.getName());
                user.setAge(Integer.valueOf(userDB.getAge()));
                String birthday = userDB.getBirthday();
                LocalDateTime b = DateUtil.getLocalDateTime(birthday);
                user.setBirthday(b);
                user.setSex(Integer.valueOf(userDB.getSex()));
                user.setHeight(Double.valueOf(userDB.getHeight()));
                user.setAddress(userDB.getAddress());
                //    可以写很多处理后的数据
                user.setCredits(10.8f);
                user.setCreateTime(LocalDateTime.now());
                userDao.insert(user);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            log.error("导入数据库失败,请检查参数和查看控制台!");
            return null;
        }
        return ExcelUtil.readExcel(excel, new UserDB(), headLineNum);
    }


}
