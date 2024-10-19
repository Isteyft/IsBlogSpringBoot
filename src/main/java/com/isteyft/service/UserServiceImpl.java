package com.isteyft.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isteyft.mapper.UserMapper;
import com.isteyft.pojo.User;
import com.isteyft.pojo.Wallpaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        return userMapper.getByUsernameAndPassword(username, password);
    }

    @Override
    public Boolean reg(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        boolean e = userMapper.RegByUsernameAndPassword(username, password);
        return e;
    }
}
