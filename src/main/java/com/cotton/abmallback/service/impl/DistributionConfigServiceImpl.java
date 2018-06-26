package com.cotton.abmallback.service.impl;

import com.cotton.base.service.impl.BaseServiceImpl;
import com.cotton.abmallback.model.DistributionConfig;
import com.cotton.abmallback.service.DistributionConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * DistributionConfig
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DistributionConfigServiceImpl extends BaseServiceImpl<DistributionConfig> implements DistributionConfigService {
}