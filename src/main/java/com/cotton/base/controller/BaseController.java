package com.cotton.base.controller;

import com.cotton.base.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cotton.base.common.RestResponse;
import com.cotton.base.service.ServiceException;

/**
 * BaseController
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/10
 */

public class BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler
	@ResponseBody
	public RestResponse<Void> handelException(Exception ex) {
		logger.error("exception", ex);

		if (ex instanceof ServiceException) {
			ServiceException serviceException = (ServiceException) ex;
			return new RestResponse<Void>(Integer.valueOf(serviceException.getCode()),
					serviceException.getMessage());

		} else if(ex instanceof MissingServletRequestParameterException){
			return new RestResponse<Void>(Constants.RcError, "参数错误");
		}else {
			// TODO 不暴露服务器错误消息
			return new RestResponse<Void>(Constants.RcError, "服务器出小差了");
		}
	}
}
