package com.isteyft.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isteyft.pojo.Result;
import com.isteyft.pojo.User;
import com.isteyft.pojo.Wallpaper;
import com.isteyft.service.UserService;
import com.isteyft.service.WpService;
import com.isteyft.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1/wp")
public class WPController {
    @Autowired
    private WpService wpService;

    @GetMapping("/wallpapers")
    public Result getWallpapers(@RequestParam String username, @RequestParam int pageNum,
                                             @RequestParam int pageSize,@RequestParam(defaultValue = "") String ss){

        log.info("登录: {}, pageNum: {}, pageSize: {},ss:{}", username, pageNum, pageSize,ss);
        PageInfo<Wallpaper> wallpapers = wpService.getWallpapers(username, pageNum, pageSize,ss);
        Integer count = wpService.getwp(username, ss);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("wallpaperlist", wallpapers);
        dataMap.put("count", count);
        return Result.success(dataMap);
    }
}
