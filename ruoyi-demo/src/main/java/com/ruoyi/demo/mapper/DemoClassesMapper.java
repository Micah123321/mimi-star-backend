package com.ruoyi.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.demo.domain.DemoClasses;

/**
 * 主子Mapper接口
 * 
 * @author Micah
 * @date 2023-07-23
 */
public interface DemoClassesMapper extends BaseMapper<DemoClasses> {
    /**
     * 查询主子
     * 
     * @param classesId 主子主键
     * @return 主子
     */
    public DemoClasses selectDemoClassesByClassesId(Long classesId);
}
