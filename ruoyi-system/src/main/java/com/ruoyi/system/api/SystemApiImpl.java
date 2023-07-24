package com.ruoyi.system.api;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruoyi.common.api.SystemApi;
import com.ruoyi.common.core.domain.entity.SysLogininfor;
import com.ruoyi.common.core.domain.entity.SysOperLog;
import com.ruoyi.system.service.ISysLogininforService;
import com.ruoyi.system.service.ISysOperLogService;

/**
 * 系统管理api接口提供者
 *
 * @author metaee
 * @date 2023/3/8 11:24
 **/
@Service
public class SystemApiImpl implements SystemApi {

	@Resource
    private ISysLogininforService sysLogininforService;
	
	@Resource
    private ISysOperLogService sysOperLogService;
    
	/**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
	@Override
	public void insertLogininfor(SysLogininfor logininfor) {
		sysLogininforService.insertLogininfor(logininfor);
	}
	
	/**
     * 新增操作日志
     * 
     * @param operLog 操作日志对象
     */
	@Override
	public void insertOperlog(SysOperLog operLog) {
		sysOperLogService.insertOperlog(operLog);
	}

}
