package com.population.flow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.population.flow.entity.Collection;
import com.population.flow.entity.CollectionVo1;
import com.population.flow.entity.CollectionVo2;

import java.util.List;

public interface CollectionService extends IService<Collection> {
    List<CollectionVo1> PieChart_Data();

    List<CollectionVo2> LineChart_Data();
}
