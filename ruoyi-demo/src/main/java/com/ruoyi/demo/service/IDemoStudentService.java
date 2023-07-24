package com.ruoyi.demo.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.demo.domain.DemoStudent;

/**
 * 单Service接口
 * 
 * @author Micah
 * @date 2023-07-23
 */
public interface IDemoStudentService extends IService<DemoStudent> {
    /**
     * 查询单列表
     * 
     * @param demoStudent 单
     * @return 单集合
     */
    public List<DemoStudent> selectDemoStudentList(DemoStudent demoStudent);
}
