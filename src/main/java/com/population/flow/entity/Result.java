package com.population.flow.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Result {
    private int code;

    private String message = "success";

    private Object data;

    public Result(int i, String s, Object o) {
        this.code=i;
        this.message=s;
        this.data=o;
    }

    public Result() {
    }

    public Result setCode(int code){
        this.code = code;
        return this;
    }

    public Result setMessage(String message){
        this.message = message;
        return this;
    }

    public Result setData(Object data){
        this.data = data;
        return this;
    }
}
