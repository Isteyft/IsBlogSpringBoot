package com.isteyft.mapper;

import com.isteyft.pojo.User;
import com.isteyft.pojo.Wallpaper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface UserMapper {

    User getByUsernameAndPassword(String username,String password);


    Boolean RegByUsernameAndPassword(String username, String password);
}
