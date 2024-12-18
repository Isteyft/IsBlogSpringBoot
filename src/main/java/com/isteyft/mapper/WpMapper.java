package com.isteyft.mapper;

import com.isteyft.pojo.Wallpaper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WpMapper {
    List<Wallpaper> findByUsername(String username, String ss);

    Integer getwp(String username, String ss);

    void saveWall(String uniqueFileName, String labels, String username, String tag);

    void delwp(String wallpaperId);

    Wallpaper getid(String id);

    Integer upWall(String wallpaperId, String labels, String tag);
}
