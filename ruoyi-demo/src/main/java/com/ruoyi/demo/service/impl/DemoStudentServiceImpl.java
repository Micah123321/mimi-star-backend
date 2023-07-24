package com.ruoyi.demo.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.demo.mapper.DemoStudentMapper;
import com.ruoyi.demo.domain.DemoStudent;
import com.ruoyi.demo.service.IDemoStudentService;

/**
 * 单Service业务层处理
 * 
 * @author Micah
 * @date 2023-07-23
 */
@Service
public class DemoStudentServiceImpl extends ServiceImpl<DemoStudentMapper, DemoStudent> implements IDemoStudentService {
    @Autowired
    private DemoStudentMapper demoStudentMapper;
    
    /**
     * 查询单列表
     * 
     * @param demoStudent 单
     * @return 单
     */
    @Override
    public List<DemoStudent> selectDemoStudentList(DemoStudent demoStudent) {
    	LambdaQueryWrapper<DemoStudent> queryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotNull(demoStudent.getClassesId())) {
			queryWrapper.eq(DemoStudent::getClassesId, demoStudent.getClassesId());
		}
        if (StringUtils.isNotEmpty(demoStudent.getStudentName())) {
			queryWrapper.like(DemoStudent::getStudentName, demoStudent.getStudentName());
		}
        if (StringUtils.isNotNull(demoStudent.getStudentAge())) {
			queryWrapper.eq(DemoStudent::getStudentAge, demoStudent.getStudentAge());
		}
        if (StringUtils.isNotEmpty(demoStudent.getStudentSex())) {
			queryWrapper.eq(DemoStudent::getStudentSex, demoStudent.getStudentSex());
		}
        if (StringUtils.isNotNull(demoStudent.getStudentBirthday())) {
			queryWrapper.eq(DemoStudent::getStudentBirthday, demoStudent.getStudentBirthday());
		}
        if (StringUtils.isNotEmpty(demoStudent.getSTATUS())) {
			queryWrapper.eq(DemoStudent::getSTATUS, demoStudent.getSTATUS());
		}
        return demoStudentMapper.selectList(queryWrapper);
    }
}
