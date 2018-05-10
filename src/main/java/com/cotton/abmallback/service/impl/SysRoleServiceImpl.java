package com.cotton.abmallback.service.impl;

import com.cotton.base.service.impl.BaseServiceImpl;
import com.cotton.abmallback.model.SysRole;
import com.cotton.abmallback.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/10
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {
}