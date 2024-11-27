package com.isteyft.controller;

import com.github.pagehelper.PageInfo;
import com.isteyft.pojo.Boke;
import com.isteyft.pojo.Pl;
import com.isteyft.pojo.Result;
import com.isteyft.service.BokeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Slf4j
@RestController
@RequestMapping("/v2/boke")
public class AdminBokeController {

    @Autowired
    private BokeService bokeService;
    //删除博客
    @DeleteMapping("/sc")
    public void delbk(String bokeId){
        bokeService.delbk(bokeId);
    }
    //上传博客
    @PostMapping("/upload")
    public Result handleUpload
            (@RequestBody Boke boke) {
        String bokeid = UUID.randomUUID().toString();
        String istop = new String();
        if ("true".equalsIgnoreCase(boke.getTop())) {
            istop = "yes";
        }else {
            istop = "no";
        }
        return Result.success(bokeService.uploadbk(bokeid,boke.getTitle(),boke.getTxt(),boke.getUsername(),istop,boke.getImgurl(),boke.getTag()));
    }
    //更新博客
    @PutMapping("/update")
    @ResponseBody
    public Result handleUpdate
            (@RequestBody Boke boke) {
//        log.info("title:{},imgurl:{},txt:{},top:({})",title,imgurl,txt,top);
        String istop = new String();
        if ("true".equalsIgnoreCase(boke.getTop())) {
            istop = "yes";
        }else {
            istop = "no";
        }
        return Result.success(bokeService.updatebk(boke.getBokeId(),boke.getTitle(),boke.getTxt(),boke.getUsername(),istop,boke.getImgurl(),boke.getTag()));
    }
    //根据评论id获取评论，用于修改
    @GetMapping("/plid")
    public Result getplid(String plid){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("pinlun", bokeService.getplid(plid));
        return Result.success(dataMap);
    }
    //管理页面用于查找评论
    @GetMapping("/pls")
    public Result getpls( @RequestParam int pageNum,
                          @RequestParam int pageSize, @RequestParam(defaultValue = "") String ss){

        log.info("pageNum: {}, pageSize: {},ss:{}", pageNum, pageSize,ss);
        Integer plss = bokeService.getplss(ss);
        PageInfo<Pl> pinlun = bokeService.getpls(pageNum, pageSize,ss);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("pinlunlist", pinlun);
        dataMap.put("count", plss);
        return Result.success(dataMap);
    }
    //更新评论
    @PutMapping("/updatepl")
    @ResponseBody
    public Result updatepl
    (@RequestBody Pl pl){
        return Result.success(bokeService.updatepl(pl.getPlid(),pl.getTxt(),pl.getUsername()));
    }
    //删除评论
    @DeleteMapping("/scpl")
    public void delpl(String plid){
        bokeService.delpl(plid);
    }
}
