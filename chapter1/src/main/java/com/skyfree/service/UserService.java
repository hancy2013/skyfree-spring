package com.skyfree.service;

import com.skyfree.dao.LoginLogDao;
import com.skyfree.dao.UserDao;
import com.skyfree.domain.LoginLog;
import com.skyfree.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/17 11:17
 */
@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private LoginLogDao loginLogDao;
    
    public boolean hasMatchUser(String userName, String password){
        int matchCount = userDao.getMatchCount(userName,password);
        return matchCount > 0;
    }
    
    public User findUserByUserName(String userName){
        return userDao.findUserByUserName(userName);
    }
    
    public void loginSuccess(User user){
        LoginLog log = new LoginLog();
        
        log.setUserId(user.getUserId());
        log.setIp(user.getLastIp());
        log.setLoginDate(user.getLastVisit());
        
        loginLogDao.insertLoginLog(log);
    }
}
