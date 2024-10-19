package com.isteyft.service;

import com.github.pagehelper.PageInfo;
import com.isteyft.pojo.Wallpaper;
import org.springframework.stereotype.Service;

public interface WpService {
    PageInfo<Wallpaper> getWallpapers(String username, Integer pageNum, Integer pageSize, String ss);

    Integer getwp(String username, String ss);

    void saveFile(String uniqueFileName, String tag,String username);

    void delwp(String wallpaperId);

    Wallpaper getid(String id);

    Integer upFile(String wallpaperId, String tag);
}
