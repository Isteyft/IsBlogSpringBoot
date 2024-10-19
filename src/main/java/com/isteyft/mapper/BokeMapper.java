package com.isteyft.mapper;

import com.github.pagehelper.PageInfo;
import com.isteyft.pojo.Boke;
import com.isteyft.pojo.Pl;
import com.isteyft.pojo.Wallpaper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BokeMapper {

    public List<Boke> getzd();


    List<Boke> findByUsername(String username, String ss);

    Integer getbk(String username, String ss);

    Boke getid(String id);

    void delbk(String bokeId);

    Integer uploadbk(String bokeid, String title, String txt, String username, String istop, String imgurl);

    Integer updatebk(String bokeid, String title, String txt, String username, String istop, String imgurl);

    List<Pl> getpl(String bokeId);

    Integer uploadpl(String bokeid, String txt, String username);

    void delpl(String plid);

    Integer updatepl(String plid, String txt, String username);

    Integer getplss(String ss);

    List<Pl> findpl(String ss);

    Pl getplid(String plid);
}
