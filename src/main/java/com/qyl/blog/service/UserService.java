package com.qyl.blog.service;

import com.qyl.blog.po.User;

public interface UserService {

    User checkUser(String username, String password);
}
