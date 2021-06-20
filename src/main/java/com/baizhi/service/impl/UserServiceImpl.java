package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public Map<String, Object> login(User user) {
        log.info("{}",user);
        HashMap<String, Object> map = new HashMap<>();
        try {
            User userDB=userDao.login(user.getUsername());
            if (userDB==null)throw new RuntimeException("用户名错误");
            if (!user.getPassword().equals(userDB.getPassword())) throw new RuntimeException("密码错误");
            map.put("status",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg",e.getMessage());
            map.put("status",false);
        }
        return map;
    }
}
