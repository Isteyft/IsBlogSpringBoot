package com.isteyft.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isteyft.mapper.UserMapper;
import com.isteyft.mapper.WpMapper;
import com.isteyft.pojo.Wallpaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WpServiceImpl implements WpService {

    @Autowired
    private WpMapper wpMapper;
    @Override
    public PageInfo<Wallpaper> getWallpapers(String username, Integer pageNum, Integer pageSize, String ss) {
        PageHelper.startPage(pageNum, pageSize);
        List<Wallpaper> wallpapers = wpMapper.findByUsername(username, ss);

        return new PageInfo<>(wallpapers);
    }

    @Override
    public Integer getwp(String username, String ss) {
        return wpMapper.getwp(username, ss);
    }

    @Override
    public void saveFile(String uniqueFileName, String tag, String username) {
        wpMapper.saveWall(uniqueFileName, tag, username);
    }

    @Override
    public void delwp(String wallpaperId) {
        wpMapper.delwp(wallpaperId);
    }

    @Override
    public Wallpaper getid(String id) {
        return wpMapper.getid(id);
    }

    @Override
    public Integer upFile(String wallpaperId, String tag) {
        return wpMapper.upWall(wallpaperId, tag);
    }

}
