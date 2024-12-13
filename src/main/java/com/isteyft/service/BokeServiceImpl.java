package com.isteyft.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isteyft.mapper.BokeMapper;
import com.isteyft.pojo.Boke;
import com.isteyft.pojo.CPl;
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
    public PageInfo<Boke> getbokesbytag(String username, int pageNum, int pageSize, String ss) {
        PageHelper.startPage(pageNum, pageSize);
        List<Boke> boke = bokeMapper.findByUsernamebytag(username, ss);
        return new PageInfo<>(boke);
    }

    @Override
    public PageInfo<Boke> getbokes(String username, int pageNum, int pageSize, String ss) {
        PageHelper.startPage(pageNum, pageSize);
        List<Boke> boke = bokeMapper.findByUsername(username, ss);
        return new PageInfo<>(boke);
    }

    @Override
    public Integer getbkbytag(String username, String ss) {
        return bokeMapper.getbkbytag(username, ss);
    }

    @Override
    public Integer getbk(String username, String ss) {
        return bokeMapper.getbk(username, ss);
    }

    @Override
    public Boke getid(String id) {
        Boke boke = bokeMapper.getid(id);
        bokeMapper.addvisit(id,boke.getVisit()+1);
        return boke;
    }

    @Override
    public void delbk(String bokeId) {
        bokeMapper.delbk(bokeId);
    }

    @Override
    public Integer uploadbk(String bokeid, String title, String txt, String username, String istop, String imgurl, String tag) {
        return bokeMapper.uploadbk(bokeid,title,txt,username,istop,imgurl,tag);
    }

    @Override
    public Integer updatebk(String bokeid, String title, String txt, String username, String istop, String imgurl, String tag) {
        return bokeMapper.updatebk(bokeid,title,txt,username,istop,imgurl,tag);

    }

    @Override
    public List<Pl> getpl(String bokeId) {
        List<Pl> pls = bokeMapper.getpl(bokeId);
        for (Pl pl : pls) {
            pl.setCpls(bokeMapper.getcpl(pl.getPlid()));
        }
        return pls;
    }

    @Override
    public Integer uploadpl(String bokeid, String txt, String username) {
        return bokeMapper.uploadpl(bokeid,txt,username);
    }

    @Override
    public Integer uploadcpl(String bokeid, String txt, String username, String plid, String replyTo) {
        return bokeMapper.uploadcpl(bokeid,txt,username,plid,replyTo);
    }

    @Override
    public void delpl(String plid) {
        bokeMapper.delpl(plid);
        CPl[] cpls = bokeMapper.getcpl(plid);
        for (CPl cpl : cpls) {
            bokeMapper.delcpl(cpl.getCplid());
        }
    }

    @Override
    public void delcpl(String cplid) {
        bokeMapper.delcpl(cplid);
    }

    @Override
    public Integer updatepl(String plid, String txt, String username) {
        return bokeMapper.updatepl(plid,txt,username);
    }

    @Override
    public Integer updatecpl(String cplid, String txt, String username) {
        return bokeMapper.updatecpl(cplid,txt,username);
    }

    @Override
    public Integer getplss(String ss) {
        return bokeMapper.getplss(ss);
    }

    @Override
    public PageInfo<Pl> getpls(int pageNum, int pageSize, String ss) {
        PageHelper.startPage(pageNum, pageSize);
        List<Pl> pls = bokeMapper.findpl(ss);
        for (Pl pl : pls) {
            pl.setCpls(bokeMapper.getcpl(pl.getPlid()));
        }
        return new PageInfo<>(pls);
    }

    @Override
    public Pl getplid(String plid) {
        return bokeMapper.getplid(plid);
    }

    @Override
    public Integer getcplss(String ss, String plid) {
        return bokeMapper.getcplss(ss, plid);
    }

    @Override
    public PageInfo<CPl> getcpls(int pageNum, int pageSize, String ss, String plid) {
        PageHelper.startPage(pageNum, pageSize);
        List<CPl> pls = bokeMapper.findcpl(ss, plid);
        return new PageInfo<>(pls);
    }
}
