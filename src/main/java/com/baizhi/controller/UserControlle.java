package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("user")
public class UserControlle {
    @Autowired
    private UserService userService;
    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(User user, HttpServletResponse response, HttpSession session){
        Map<String, Object> map;
        map = userService.login(user);
        if ((boolean)map.get("status")) session.setAttribute("user",user);
        return map;
    }
    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/menu/back/login.jsp";
    }
}
