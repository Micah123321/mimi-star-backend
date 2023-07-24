package com.ruoyi.demo.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.StringUtils;
import java.util.Arrays;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.demo.domain.DemoStudent;
import com.ruoyi.demo.mapper.DemoStudentMapper;
import com.ruoyi.demo.mapper.DemoClassesMapper;
import com.ruoyi.demo.domain.DemoClasses;
import com.ruoyi.demo.service.IDemoClassesService;

/**
 * 主子Service业务层处理
 * 
 * @author Micah
 * @date 2023-07-23
 */
@Service
public class DemoClassesServiceImpl extends ServiceImpl<DemoClassesMapper, DemoClasses> implements IDemoClassesService {
    @Autowired
    private DemoClassesMapper demoClassesMapper;

    @Autowired
    private DemoStudentMapper demoStudentMapper;
    
    /**
     * 查询主子列表
     * 
     * @param demoClasses 主子
     * @return 主子
     */
    @Override
    public List<DemoClasses> selectDemoClassesList(DemoClasses demoClasses) {
    	LambdaQueryWrapper<DemoClasses> queryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotEmpty(demoClasses.getClassesName())) {
			queryWrapper.like(DemoClasses::getClassesName, demoClasses.getClassesName());
		}
        if (StringUtils.isNotNull(demoClasses.getClassesGrade())) {
			queryWrapper.eq(DemoClasses::getClassesGrade, demoClasses.getClassesGrade());
		}
        if (StringUtils.isNotEmpty(demoClasses.getClassesTeacher())) {
			queryWrapper.eq(DemoClasses::getClassesTeacher, demoClasses.getClassesTeacher());
		}
        if (StringUtils.isNotEmpty(demoClasses.getStatus())) {
			queryWrapper.eq(DemoClasses::getStatus, demoClasses.getStatus());
		}
        return demoClassesMapper.selectList(queryWrapper);
    }

    /**
     * 查询主子
     * 
     * @param classesId 主子主键
     * @return 主子
     */
    @Override
    public DemoClasses selectDemoClassesByClassesId(Long classesId) {
        return demoClassesMapper.selectDemoClassesByClassesId(classesId);
    }

    /**
     * 新增主子
     * 
     * @param demoClasses 主子
     * @return 结果
     */
    @Transactional
    @Override
    public int insertDemoClasses(DemoClasses demoClasses) {
        demoClasses.setCreateTime(DateUtils.getNowDate());
        int rows = demoClassesMapper.insert(demoClasses);
        insertDemoStudent(demoClasses);
        return rows;
    }

    /**
     * 修改主子
     * 
     * @param demoClasses 主子
     * @return 结果
     */
    @Transactional
    @Override
    public int updateDemoClasses(DemoClasses demoClasses) {
        demoClasses.setUpdateTime(DateUtils.getNowDate());
        LambdaQueryWrapper<DemoStudent> queryWrapper = Wrappers.lambdaQuery();
     	queryWrapper.eq(DemoStudent::getClassesId, demoClasses.getClassesId());
     	demoStudentMapper.delete(queryWrapper);
        
        insertDemoStudent(demoClasses);
        return demoClassesMapper.updateById(demoClasses);
    }

    /**
     * 批量删除主子
     * 
     * @param classesIds 需要删除的主子主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDemoClassesByClassesIds(Long[] classesIds) {
        LambdaQueryWrapper<DemoStudent> queryWrapper = Wrappers.lambdaQuery();
     	queryWrapper.in(DemoStudent::getClassesId, Arrays.asList(classesIds));
     	demoStudentMapper.delete(queryWrapper);
        return demoClassesMapper.deleteBatchIds(Arrays.asList(classesIds));
    }

    /**
     * 删除主子信息
     * 
     * @param classesId 主子主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDemoClassesByClassesId(Long classesId) {
        LambdaQueryWrapper<DemoStudent> queryWrapper = Wrappers.lambdaQuery();
     	queryWrapper.eq(DemoStudent::getClassesId, classesId);
     	demoStudentMapper.delete(queryWrapper);
        return demoClassesMapper.deleteById(classesId);
    }
    
    /**
     * 新增单信息
     * 
     * @param demoClasses 主子对象
     */
    public void insertDemoStudent(DemoClasses demoClasses) {
        List<DemoStudent> demoStudentList = demoClasses.getDemoStudentList();
        Long classesId = demoClasses.getClassesId();
        if (StringUtils.isNotNull(demoStudentList)) {
            for (DemoStudent demoStudent : demoStudentList) {
                demoStudent.setClassesId(classesId);
                demoStudentMapper.insert(demoStudent);
            }
        }
    }

}
