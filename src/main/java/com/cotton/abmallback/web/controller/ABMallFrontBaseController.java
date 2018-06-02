package com.cotton.abmallback.web.controller;

import com.cotton.abmallback.model.Member;
import com.cotton.abmallback.web.PermissionContext;
import com.cotton.base.controller.BaseController;

/**
 * ABMallFrontBaseController
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/10
 */
public class ABMallFrontBaseController extends BaseController {

    protected long getCurrentMemberId(){
        return 1L;
    }

    protected Member getCurrentMember(){
        return  PermissionContext.getMember();
    }
}