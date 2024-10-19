package com.isteyft.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isteyft.mapper.BokeMapper;
import com.isteyft.pojo.Boke;
import com.isteyft.pojo.Pl;
import com.isteyft.pojo.Wallpaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BokeServiceImpl implements BokeService {

    @Autowired
    private BokeMapper bokeMapper;
    @Override
    public List<Boke> getzd()
    {
        return bokeMapper.getzd();
    }

    @Override
    public PageInfo<Boke> getbokes(String username, int pageNum, int pageSize, String ss) {
        PageHelper.startPage(pageNum, pageSize);
        List<Boke> boke = bokeMapper.findByUsername(username, ss);
        return new PageInfo<>(boke);
    }

    @Override
    public Integer getbk(String username, String ss) {
        return bokeMapper.getbk(username, ss);
    }

    @Override
    public Boke getid(String id) {
        return bokeMapper.getid(id);
    }

    @Override
    public void delbk(String bokeId) {
        bokeMapper.delbk(bokeId);
    }

    @Override
    public Integer uploadbk(String bokeid, String title, String txt, String username, String istop, String imgurl) {
        return bokeMapper.uploadbk(bokeid,title,txt,username,istop,imgurl);
    }

    @Override
    public Integer updatebk(String bokeid, String title, String txt, String username, String istop, String imgurl) {
        return bokeMapper.updatebk(bokeid,title,txt,username,istop,imgurl);

    }

    @Override
    public List<Pl> getpl(String bokeId) {
        return bokeMapper.getpl(bokeId);
    }

    @Override
    public Integer uploadpl(String bokeid, String txt, String username) {
        return bokeMapper.uploadpl(bokeid,txt,username);
    }

    @Override
    public void delpl(String plid) {
        bokeMapper.delpl(plid);
    }

    @Override
    public Integer updatepl(String plid, String txt, String username) {
        return bokeMapper.updatepl(plid,txt,username);
    }

    @Override
    public Integer getplss(String ss) {
        return bokeMapper.getplss(ss);
    }

    @Override
    public PageInfo<Pl> getpls(int pageNum, int pageSize, String ss) {
        PageHelper.startPage(pageNum, pageSize);
        List<Pl> boke = bokeMapper.findpl(ss);
        return new PageInfo<>(boke);
    }

    @Override
    public Pl getplid(String plid) {
        return bokeMapper.getplid(plid);
    }
}
