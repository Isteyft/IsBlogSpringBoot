package com.isteyft.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.isteyft.utils.FlexibleDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Boke {
    private String bokeId;
    private String username;
    private String title;
    private String imgurl;
    private String txt;
    @JsonDeserialize(using = FlexibleDateDeserializer.class)
    private Date loadTime;
    @JsonDeserialize(using = FlexibleDateDeserializer.class)
    private Date uploadTime;
    private String top;
}
