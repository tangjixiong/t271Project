package com.t271.controller;

import com.t271.entity.User;
import com.t271.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Resource(name="userService")
    private UserService userService;


    //进入登录页面
    @RequestMapping("/login.html")
    public  String tologin(){
        return "login";
    }

    //处理登录请求
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String dologin(String userCode, String userPassword, Model model,
                          HttpSession session){
        //验证用户名和密码
        User user=userService.login(userCode,userPassword);
        if(user!=null){
            //登录成功
            //把用户信息保存到session
            session.setAttribute("userSession",user);
            return "redirect:/sys/main.html";
        }else{
            //登录失败
            model.addAttribute("error","用   户名或密码错误");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public  String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }
}
