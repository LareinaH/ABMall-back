package com.cotton.abmallback.service.impl;

import com.cotton.base.service.impl.BaseServiceImpl;
import com.cotton.abmallback.model.ShopActivities;
import com.cotton.abmallback.service.ShopActivitiesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ShopActivities
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopActivitiesServiceImpl extends BaseServiceImpl<ShopActivities> implements ShopActivitiesService {
}