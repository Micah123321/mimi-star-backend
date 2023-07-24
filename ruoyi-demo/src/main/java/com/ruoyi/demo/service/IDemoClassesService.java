package com.ruoyi.demo.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.demo.domain.DemoClasses;

/**
 * 主子Service接口
 * 
 * @author Micah
 * @date 2023-07-23
 */
public interface IDemoClassesService extends IService<DemoClasses> {
    /**
     * 查询主子列表
     * 
     * @param demoClasses 主子
     * @return 主子集合
     */
    public List<DemoClasses> selectDemoClassesList(DemoClasses demoClasses);
    /**
     * 查询主子
     * 
     * @param classesId 主子主键
     * @return 主子
     */
    public DemoClasses selectDemoClassesByClassesId(Long classesId);

    /**
     * 新增主子
     * 
     * @param demoClasses 主子
     * @return 结果
     */
    public int insertDemoClasses(DemoClasses demoClasses);

    /**
     * 修改主子
     * 
     * @param demoClasses 主子
     * @return 结果
     */
    public int updateDemoClasses(DemoClasses demoClasses);

    /**
     * 批量删除主子
     * 
     * @param classesIds 需要删除的主子主键集合
     * @return 结果
     */
    public int deleteDemoClassesByClassesIds(Long[] classesIds);

    /**
     * 删除主子信息
     * 
     * @param classesId 主子主键
     * @return 结果
     */
    public int deleteDemoClassesByClassesId(Long classesId);
}
