package com.isteyft.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Wallpaper {
    private String wallpaperId;
    private String username;
    private String labels;
    private Date loadTime;
    private Date uploadTime;
}
