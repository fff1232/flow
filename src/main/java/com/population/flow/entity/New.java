package com.population.flow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Comparator;
import java.util.Date;

@Data
public class New implements Comparator<New> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private String img;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date date;

    @Override
    public int compare(New o1, New o2) {
        if (o1.getId()>o2.getId()){
            return -1;
        } else {
            return 1;
        }
    }
}
