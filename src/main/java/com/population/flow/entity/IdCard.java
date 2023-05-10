package com.population.flow.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class IdCard implements Serializable {
    private String name;
    private String address;
    private String num;
    private String sex;
    private String birth;
}
