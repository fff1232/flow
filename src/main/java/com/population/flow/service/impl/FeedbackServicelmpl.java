package com.population.flow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.population.flow.entity.Feedback;
import com.population.flow.mapper.FeedbackMapper;
import com.population.flow.service.FeedbackService;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServicelmpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {
}
