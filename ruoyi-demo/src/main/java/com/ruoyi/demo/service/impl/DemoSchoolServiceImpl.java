package com.ruoyi.demo.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.demo.mapper.DemoSchoolMapper;
import com.ruoyi.demo.domain.DemoSchool;
import com.ruoyi.demo.service.IDemoSchoolService;

/**
 * 树Service业务层处理
 * 
 * @author Micah
 * @date 2023-07-23
 */
@Service
public class DemoSchoolServiceImpl extends ServiceImpl<DemoSchoolMapper, DemoSchool> implements IDemoSchoolService {
    @Autowired
    private DemoSchoolMapper demoSchoolMapper;
    
    /**
     * 查询树列表
     * 
     * @param demoSchool 树
     * @return 树
     */
    @Override
    public List<DemoSchool> selectDemoSchoolList(DemoSchool demoSchool) {
    	LambdaQueryWrapper<DemoSchool> queryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotNull(demoSchool.getParentId())) {
			queryWrapper.eq(DemoSchool::getParentId, demoSchool.getParentId());
		}
        if (StringUtils.isNotEmpty(demoSchool.getAncestors())) {
			queryWrapper.eq(DemoSchool::getAncestors, demoSchool.getAncestors());
		}
        if (StringUtils.isNotNull(demoSchool.getOrderNum())) {
			queryWrapper.eq(DemoSchool::getOrderNum, demoSchool.getOrderNum());
		}
        if (StringUtils.isNotEmpty(demoSchool.getSchoolName())) {
			queryWrapper.like(DemoSchool::getSchoolName, demoSchool.getSchoolName());
		}
        if (StringUtils.isNotEmpty(demoSchool.getStatus())) {
			queryWrapper.eq(DemoSchool::getStatus, demoSchool.getStatus());
		}
        return demoSchoolMapper.selectList(queryWrapper);
    }
}
