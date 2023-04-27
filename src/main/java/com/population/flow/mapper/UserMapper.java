package com.population.flow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.population.flow.entity.User;
import com.population.flow.entity.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT CASE \n" +
            "\tWHEN year(CURDATE())-year(birthday)<18 THEN \"少年\"\n" +
            "\tWHEN year(CURDATE())-year(birthday)<35 THEN \"青年\"\n" +
            "\tWHEN year(CURDATE())-year(birthday)<55 THEN \"中年\"\n" +
            "\tELSE\n" +
            "\t\t\"老年\" end AS 'label',\n" +
            "\tCOUNT(*) AS number\n" +
            "FROM `user`\n" +
            "GROUP BY label\n")
    List<UserVo> RingChart_Data();

    @Select("select CASE sex\n" +
            "\tWHEN 0 THEN '男'\n" +
            "\tELSE\n" +
            "\t\t'女'\n" +
            "\tend as 'label'\n" +
            ",COUNT(sex) as 'number' from user GROUP BY sex")
    List<UserVo> RingChart_Data2();
}
