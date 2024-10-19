package com.isteyft.service;

import com.github.pagehelper.PageInfo;
import com.isteyft.pojo.User;
import com.isteyft.pojo.Wallpaper;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    public User login(User user);

    Boolean reg(User user);

}
