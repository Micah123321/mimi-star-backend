package com.ruoyi.demo.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.demo.domain.DemoSchool;

/**
 * 树Service接口
 * 
 * @author Micah
 * @date 2023-07-23
 */
public interface IDemoSchoolService extends IService<DemoSchool> {
    /**
     * 查询树列表
     * 
     * @param demoSchool 树
     * @return 树集合
     */
    public List<DemoSchool> selectDemoSchoolList(DemoSchool demoSchool);
}
