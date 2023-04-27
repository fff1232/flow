package com.population.flow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.population.flow.entity.Collection;
import com.population.flow.entity.CollectionVo1;
import com.population.flow.entity.CollectionVo2;
import com.population.flow.mapper.CollectionMapper;
import com.population.flow.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {
    @Autowired
    CollectionMapper mapper;
    @Override
    public List<CollectionVo1> PieChart_Data() {
        return mapper.piechart_data();
    }

    @Override
    public List<CollectionVo2> LineChart_Data() {
        return mapper.linechart_data();
    }
}
