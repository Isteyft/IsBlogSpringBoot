package com.isteyft.mapper;

import com.isteyft.pojo.Boke;
import com.isteyft.pojo.CPl;
import com.isteyft.pojo.Pl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BokeMapper {

    public List<Boke> getzd();


    List<Boke> findByUsername(String username, String ss);
    List<Boke> findByUsernamebytag(String username, String ss);

    Integer getbk(String username, String ss);

    Integer getbkbytag(String username, String ss);

    Boke getid(String id);

    void delbk(String bokeId);

    Integer uploadbk(String bokeid, String title, String txt, String username, String istop, String imgurl, String tag);

    Integer updatebk(String bokeid, String title, String txt, String username, String istop, String imgurl, String tag);

    List<Pl> getpl(String bokeId);
    CPl[] getcpl(String plid);

    Integer uploadpl(String bokeid, String txt, String username);

    void delpl(String plid);

    Integer updatepl(String plid, String txt, String username);

    Integer getplss(String ss);

    List<Pl> findpl(String ss);

    Pl getplid(String plid);

    void addvisit(String id, int visit);

    Integer uploadcpl(String bokeid, String txt, String username, String plid, String replyTo);

    void delcpl(String cplid);

    Integer updatecpl(String cplid, String txt, String username);

    Integer getcplss(String ss, String plid);

    List<CPl> findcpl(String ss, String plid);
}
