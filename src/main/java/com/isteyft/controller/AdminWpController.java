package com.isteyft.controller;

import com.isteyft.pojo.Result;
import com.isteyft.pojo.Wallpaper;
import com.isteyft.service.UserService;
import com.isteyft.service.WpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v2/wp")
public class AdminWpController {
    @Autowired
    private WpService wpService;

    @GetMapping("/id")
    public Result getid(String id){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("wallpaper", wpService.getid(id));
        return Result.success(dataMap);
    }

    @DeleteMapping("/sc")
    public void delwp(String wallpaperId){
        wpService.delwp(wallpaperId);
    }

    @PostMapping("/upload")
    public Result handleFileUpload
            (@RequestParam("file") MultipartFile file, @RequestParam("tag") String tag, @RequestParam("username") String username) {
        if (!file.isEmpty()) {
            // 获取原始文件名和扩展名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            // 生成UUID作为文件名主体
            String fileNameWithoutExt = UUID.randomUUID().toString();
            // 合成新的文件名（包含UUID和原始扩展名）
            String uniqueFileName = fileNameWithoutExt + '.' + extension;
            // 定义存储路径
            String uploadPath = "/ServerImage/";
            // 拼接完整的本地文件路径
            Path destinationPath = Paths.get(uploadPath, uniqueFileName);
            try {
                // 创建父目录（如果不存在）
                Files.createDirectories(destinationPath.getParent());
                // 将上传的文件写入到指定路径
                file.transferTo(destinationPath.toFile());
                // 调用FileService来处理数据库持久化
                wpService.saveFile(uniqueFileName,tag,username);
                return Result.success("上传成功ID为"+ uniqueFileName);
            } catch (IOException e) {
                throw new RuntimeException("错误的文件", e);
            }
        } else {
            throw new RuntimeException("文件错误");
        }
    }

    @PutMapping("/update")
    public Result handleFileUpdate
            (@RequestParam("wallpaperId") String wallpaperId,
             @RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("tag") String tag) {
        if (file != null && !file.isEmpty()) {
            // 获取原始文件名和扩展名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String filenameWithoutExtension = wallpaperId.substring(0, wallpaperId.lastIndexOf("."));
            // 合成新的文件名（包含UUID和原始扩展名）
            String uniqueFileName = filenameWithoutExtension + '.' + extension;
            // 定义存储路径
            String uploadPath = "/ServerImage/";
            // 拼接完整的本地文件路径
            Path destinationPath = Paths.get(uploadPath, uniqueFileName);
            try {
                // 创建父目录（如果不存在）
                Files.createDirectories(destinationPath.getParent());
                // 将上传的文件写入到指定路径
                file.transferTo(destinationPath.toFile());
                // 调用FileService来处理数据库持久化
                wpService.upFile(wallpaperId,tag);
                return Result.success("上传成功ID为"+ uniqueFileName);
            } catch (IOException e) {
                throw new RuntimeException("错误的文件", e);
            }
        } else {
            wpService.upFile(wallpaperId, tag);
            return Result.success("表情更新成功"+ wallpaperId);
        }
    }

}
