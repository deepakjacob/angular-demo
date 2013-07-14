package com.dj.ngapp.core;

import com.dj.ngapp.core.model.User;

public class UserUtil {
    
    static public ThreadLocal<User> USER = new ThreadLocal<User>();
    
    static public User getUser(){
        return USER.get();
    }
}
