package com.bbt.back.web;

import com.bbt.back.entities.Admin;
import com.bbt.back.entities.User;
import com.bbt.back.enums.LoginResultEnum;
import com.bbt.back.exception.LoginException;
import com.bbt.back.model.AdminDto;
import com.bbt.back.model.ResultEntity;
import com.bbt.back.model.UserDto;
import com.bbt.back.service.AdminService;
import com.bbt.back.service.LoginService;
import com.bbt.back.service.UserService;
import com.bbt.back.utils.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 21:06
 */
@RestController
@RequestMapping("")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @PostMapping("/user/login")
    private Object loginByUser(HttpServletRequest request) {
        ResultEntity resultEntity=new ResultEntity();
        //1.根据前端传递的参数发起登录请求
        String userEmail = HttpServletRequestUtil.getString(request, "userEmail");
        String password = HttpServletRequestUtil.getString(request, "password");
        try {
            ResultEntity result = loginService.loginByUser(userEmail, password);
            if (result.getCode().intValue() == LoginResultEnum.SUCCESS.getCode().intValue()) {
                resultEntity.setCode(200);
                resultEntity.setMsg(result.getMsg());
                resultEntity.setData(result.getData());
            }else{
                resultEntity.setCode(500);
                resultEntity.setMsg(result.getMsg());
                return resultEntity;
            }
            User user=(User)result.getData();
            UserDto userDto=new UserDto(user.getUserId());
            resultEntity.setData(userDto);
            //将用户信息存入session中
            if (result.getData() != null) {
                List<User> userList = new ArrayList<>();
                if (null != request.getSession().getAttribute("userList")) {
                    //如果userList已经存在
                    userList = (List<User>) request.getSession().getAttribute("userList");
                }
                //添加user到 userList
                userList.add((User) result.getData());
                request.getSession().setAttribute("userList", userList);
            }
        } catch (LoginException ex) {
            resultEntity.setCode(ex.getCode());
            resultEntity.setMsg(ex.getMessage());
        }
        return resultEntity;
    }

    @PostMapping("/admin/login")
    private Object loginByAdmin(HttpServletRequest request) {
        ResultEntity resultEntity=new ResultEntity();
        //1.根据前端传递的参数发起登录请求,默认字符串是进行了去空处理
        String adminEmail = HttpServletRequestUtil.getString(request, "adminEmail");
        String password = HttpServletRequestUtil.getString(request, "password");
        try {
            ResultEntity result = loginService.loginByAdmin(adminEmail, password);
            if (result.getCode().intValue() == LoginResultEnum.SUCCESS.getCode().intValue()) {
                resultEntity.setCode(200);
                resultEntity.setMsg(result.getMsg());
                resultEntity.setData(result.getData());
            }else{
                resultEntity.setCode(500);
                resultEntity.setMsg(result.getMsg());
                return resultEntity;
            }
            Admin admin=(Admin) result.getData();
            AdminDto adminDto=new AdminDto(admin.getAdminId());
            resultEntity.setData(adminDto);
            //将管理员信息存入session中
            if (result.getData() != null) {
                List<Admin> adminList = new ArrayList<>();
                if (null != request.getSession().getAttribute("adminList")) {
                    //如果adminList已经存在
                    adminList = (List<Admin>) request.getSession().getAttribute("adminList");
                }
                //添加admin到 adminList
                adminList.add((Admin) result.getData());
                request.getSession().setAttribute("adminList", adminList);
            }
        } catch (LoginException ex) {
            resultEntity.setMsg(ex.getMessage());
            resultEntity.setCode(ex.getCode());
        }
        return resultEntity;
    }

    @GetMapping("/user/logout")
    private Object logout(HttpServletRequest request) {
        ResultEntity resultEntity=new ResultEntity();
        //获取要logout的用户id
        Integer userId;
        try {
            userId = HttpServletRequestUtil.getInt(request, "userId");
        } catch (NumberFormatException e) {
            resultEntity.setMsg("获取用户对象ID信息异常，无法完成注销。");
            return resultEntity;
        }
        //开始尝试注销
        try {
            User user = userService.getUserById(userId);
            User userToRemove = null;
            HttpSession session = request.getSession();
            List<User> userList = (List<User>) session.getAttribute("userList");
            boolean loginYes = false;
            if (userList != null) {
                for (User oneUser : userList) {
                    //在userList中找到那个要登出的用户
                    if (//id、姓名都相同
                            oneUser.getUserId().equals(user.getUserId()) &&
                                    oneUser.getUserEmail().equals(user.getUserEmail())) {
                        //确实已经登录
                        loginYes = true;
                        userToRemove = oneUser;
                    }
                }
                //服务器未检测到该用户已登录
                if (loginYes == false) {
                    resultEntity.setMsg("服务器未检测到该用户已登录！");
                    return resultEntity;
                }
                //注销
                userList.remove(userToRemove);
            }
            //更新已登录用户列表
            session.setAttribute("userList", userList);
            resultEntity.setMsg("注销成功！");
            resultEntity.setCode(200);
            return resultEntity;
        } catch (Exception e) {
            e.printStackTrace();
            resultEntity.setCode(500);
            resultEntity.setMsg("注销过程中发生异常！");
            return resultEntity;
        }
    }

    @GetMapping("/admin/logout")
    private Object adminLogout(HttpServletRequest request) {
        ResultEntity resultEntity=new ResultEntity();
        //获取要logout的 admin用户id
        int adminId;
        try {
            adminId = HttpServletRequestUtil.getInt(request, "adminId");
            System.out.println("here:");
            System.out.println(adminId);
        } catch (NumberFormatException e) {
            resultEntity.setMsg("获取管理员对象ID信息异常，无法完成注销。");
            return resultEntity;
        }
        //开始尝试注销
        HttpSession session = request.getSession();
        List<Admin> adminList = (List<Admin>) session.getAttribute("adminList");
        boolean loginYes = false;
        try {
            System.out.println(adminId);
            Admin admin= adminService.getAdminById(adminId);
            Admin adminToRemove = null;
            if (adminList != null) {
                for (Admin oneAdmin : adminList) {
                    //在老列表里面比对，得到新的已登录列表
                    if (//id、姓名都相同
                            oneAdmin.getAdminId().equals(admin.getAdminId()) &&
                                    oneAdmin.getAdminEmail().equals(admin.getAdminEmail()) ) {
                        //确实已经登录
                        loginYes = true;
                        adminToRemove = oneAdmin;
                    }
                }
                //服务器未检测到该用户已登录
                if (loginYes == false) {
                    resultEntity.setMsg("服务器未检测到该管理员已登录！");
                    return resultEntity;
                }
                //注销
                adminList.remove(adminToRemove);
            }
            //更新已登录用户列表
            session.setAttribute("adminList", adminList);
            resultEntity.setMsg("注销成功！");
            resultEntity.setCode(200);
            return resultEntity;
        } catch (Exception e) {
            e.printStackTrace();
            resultEntity.setMsg("注销过程中发生异常！");
            resultEntity.setCode(500);
            return resultEntity;
        }
    }
}
