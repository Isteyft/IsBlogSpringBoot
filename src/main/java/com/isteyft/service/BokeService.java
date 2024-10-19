package com.isteyft.service;

import com.github.pagehelper.PageInfo;
import com.isteyft.pojo.Boke;
import com.isteyft.pojo.Pl;
import com.isteyft.pojo.Wallpaper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BokeService {
    public List<Boke> getzd();

    @Cacheable(value = "boke-list", key = "#username + '_' + #pageNum + '_' + #pageSize + '_' + #ss")
    PageInfo<Boke> getbokes(String username, int pageNum, int pageSize, String ss);

    Integer getbk(String username, String ss);

    Boke getid(String id);

    void delbk(String bokeId);

    Integer uploadbk(String bokeid, String title, String txt, String username, String istop, String imgurl);

    Integer updatebk(String bokeid, String title, String txt, String username, String istop, String imgurl);

    List<Pl> getpl(String bokeid);

    Integer uploadpl(String bokeid, String txt, String username);

    void delpl(String plid);

    Integer updatepl(String plid, String txt, String username);

    Integer getplss(String ss);

    PageInfo<Pl> getpls(int pageNum, int pageSize, String ss);

    Pl getplid(String plid);
}
