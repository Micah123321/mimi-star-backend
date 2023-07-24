package com.ruoyi.common.api;

import com.ruoyi.common.core.domain.entity.SysLogininfor;
import com.ruoyi.common.core.domain.entity.SysOperLog;

/**
 * 系统管理api接口
 *
 * @author metaee
 * @date 2023/3/8 11:24
 **/
public interface SystemApi {

	/**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
	void insertLogininfor(SysLogininfor logininfor);
	
	/**
     * 新增操作日志
     * 
     * @param operLog 操作日志对象
     */
	void insertOperlog(SysOperLog operLog);
}
