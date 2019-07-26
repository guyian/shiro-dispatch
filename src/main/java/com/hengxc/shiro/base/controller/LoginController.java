package com.hengxc.shiro.base.controller;

import com.hengxc.shiro.base.entity.User;
import com.hengxc.shiro.base.service.IUserService;
import com.hengxc.shiro.common.annotation.Limit;
import com.hengxc.shiro.common.controller.BaseController;
import com.hengxc.shiro.common.entity.FebsResponse;
import com.hengxc.shiro.common.exception.FebsException;
import com.hengxc.shiro.common.utils.CaptchaUtil;
import com.hengxc.shiro.common.utils.MD5Util;
import com.wf.captcha.Captcha;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MrBird
 */
@Validated
@RestController
public class LoginController extends BaseController {

    @Autowired
    private IUserService userService;

//    @Autowired
//    private ILoginLogService loginLogService;

    @PostMapping("login")
    @Limit(key = "login", period = 60, count = 20, name = "登录接口", prefix = "limit")
    public FebsResponse login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            /*@NotBlank(message = "{required}") String verifyCode,*/
            boolean rememberMe, HttpServletRequest request) throws FebsException {
        //验证码验证
//        if (!CaptchaUtil.verify(verifyCode, request)) {
//            throw new FebsException("验证码错误！");
//        }
        password = MD5Util.encrypt(username.toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        try {
            super.login(token);
            // 保存登录日志
//            LoginLog loginLog = new LoginLog();
//            loginLog.setUsername(username);
//            loginLog.setSystemBrowserInfo();
//            this.loginLogService.saveLoginLog(loginLog);

            return new FebsResponse().success();
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            throw new FebsException(e.getMessage());
        } catch (AuthenticationException e) {
            throw new FebsException("认证失败！");
        }
    }

    @PostMapping("regist")
    public FebsResponse regist(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password) throws FebsException {
        User user = userService.findByName(username);
        if (user != null) {
            throw new FebsException("该用户名已存在");
        }
        this.userService.regist(username, password);
        return new FebsResponse().success();
    }

    @GetMapping("index/{username}")
    public FebsResponse index(@NotBlank(message = "{required}") @PathVariable String username) {
        // 更新登录时间
        this.userService.updateLoginTime(username);
        Map<String, Object> data = new HashMap<>();
        // 获取系统访问记录
//        Long totalVisitCount = this.loginLogService.findTotalVisitCount();
//        data.put("totalVisitCount", totalVisitCount);
//        Long todayVisitCount = this.loginLogService.findTodayVisitCount();
//        data.put("todayVisitCount", todayVisitCount);
//        Long todayIp = this.loginLogService.findTodayIp();
//        data.put("todayIp", todayIp);
        // 获取近期系统访问记录
//        List<Map<String, Object>> lastSevenVisitCount = this.loginLogService.findLastSevenDaysVisitCount(null);
//        data.put("lastSevenVisitCount", lastSevenVisitCount);
        User param = new User();
        param.setUsername(username);
//        List<Map<String, Object>> lastSevenUserVisitCount = this.loginLogService.findLastSevenDaysVisitCount(param);
//        data.put("lastSevenUserVisitCount", lastSevenUserVisitCount);
        return new FebsResponse().success().data(data);
    }

    @GetMapping("images/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.outPng(110, 34, 4, Captcha.TYPE_ONLY_NUMBER, request, response);
    }
}
