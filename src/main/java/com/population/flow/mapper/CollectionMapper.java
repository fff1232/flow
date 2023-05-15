package com.population.flow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.population.flow.entity.Collection;
import com.population.flow.entity.CollectionVo1;
import com.population.flow.entity.CollectionVo2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectionMapper extends BaseMapper<Collection> {
    @Select("SELECT LEFT(address,3) as 'province',COUNT(address) as 'number' FROM `collection` GROUP BY LEFT(address,3)")
    List<CollectionVo1> piechart_data();

    @Select("select substring(date,6,2) as  'month',COUNT(date) as 'number' FROM collection GROUP BY substring(date,6,2) ORDER BY substring(date,6,2)")
    List<CollectionVo2> linechart_data();

    @Select("SELECT * FROM collection  WHERE (YEAR(NOW())-YEAR(date)) > 4")
    List<Collection> outdate();
}
