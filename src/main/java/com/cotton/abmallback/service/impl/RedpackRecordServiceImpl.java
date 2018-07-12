package com.cotton.abmallback.service.impl;

import com.cotton.base.service.impl.BaseServiceImpl;
import com.cotton.abmallback.model.RedpackRecord;
import com.cotton.abmallback.service.RedpackRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * RedpackRecord
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/7/12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RedpackRecordServiceImpl extends BaseServiceImpl<RedpackRecord> implements RedpackRecordService {
}