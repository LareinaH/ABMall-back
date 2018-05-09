package com.cotton.abmallback.service.impl;

import com.cotton.base.service.impl.BaseServiceImpl;
import com.cotton.abmallback.model.CashPickUp;
import com.cotton.abmallback.service.CashPickUpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CashPickUp
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CashPickUpServiceImpl extends BaseServiceImpl<CashPickUp> implements CashPickUpService {
}