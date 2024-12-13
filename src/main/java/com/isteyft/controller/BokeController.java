package com.isteyft.controller;

import com.github.pagehelper.PageInfo;
import com.isteyft.pojo.Boke;
import com.isteyft.pojo.CPl;
import com.isteyft.pojo.Pl;
import com.isteyft.pojo.Result;

import java.util.HashMap;
import java.util.Map;
import com.isteyft.service.BokeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/v1/boke")
public class BokeController {
    @Autowired
    private BokeService bokeService;

    //置顶的博客  List<Boke>
    @GetMapping("/zd")
    public Result getzd(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("topboke", bokeService.getzd());
        return Result.success(dataMap);
    }

    @GetMapping("/bokestag")
    public Result getbokesbytag(@RequestParam String username, @RequestParam int pageNum,
                           @RequestParam int pageSize, @RequestParam(defaultValue = "") String ss){
        log.info("登录: {}, pageNum: {}, pageSize: {},ss:{}", username, pageNum, pageSize,ss);
        //获取博客
        PageInfo<Boke> bokes = bokeService.getbokesbytag(username, pageNum, pageSize,ss);
        //博客数量，用于显示页数
        Integer bk = bokeService.getbkbytag(username, ss);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("bokelist", bokes);
        dataMap.put("count", bk);
        return Result.success(dataMap);
    }
    //获取所有博客
    @GetMapping("/bokes")
    public Result getbokes(@RequestParam String username, @RequestParam int pageNum,
                                             @RequestParam int pageSize, @RequestParam(defaultValue = "") String ss){
        log.info("登录: {}, pageNum: {}, pageSize: {},ss:{}", username, pageNum, pageSize,ss);
        //获取博客
        PageInfo<Boke> bokes = bokeService.getbokes(username, pageNum, pageSize,ss);
        //博客数量，用于显示页数
        Integer bk = bokeService.getbk(username, ss);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("bokelist", bokes);
        dataMap.put("count", bk);
        return Result.success(dataMap);
    }
    //获取评论
    @GetMapping("/pl")
    public Result getpl(String bokeId){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("pinlun", bokeService.getpl(bokeId));
        return Result.success(dataMap);
    }
    //上传评论
    @PostMapping("/uploadpl")
    public Result Uploadpl
    (@RequestBody Pl pl){
        return Result.success(bokeService.uploadpl(pl.getBokeId(),pl.getTxt(),pl.getUsername()));
    }
    //子评论
    @PostMapping("/uploadcpl")
    public Result Uploadcpl
            (@RequestBody CPl cpl){
        return Result.success(bokeService.uploadcpl(cpl.getBokeId(),cpl.getTxt(),cpl.getUsername(),cpl.getPlid(),cpl.getReplyTo()));
    }
    //用于查找文章
    @GetMapping("/id")
    public Result getid(String id){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("boke", bokeService.getid(id));
        return Result.success(dataMap);
    }
}
