package com.ruoyi.demo.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BasePlusEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 主子对象 demo_classes
 *
 * @author Micah
 * @date 2023-07-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "demo_classes")
public class DemoClasses extends BasePlusEntity {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @TableId(type = IdType.AUTO)
    private Long classesId;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String classesName;

    /** 班级年级 */
    @Excel(name = "班级年级")
    private Long classesGrade;

    /** 班主任 */
    @Excel(name = "班主任")
    private String classesTeacher;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0逻辑未删除值 1逻辑已删除值） */
    @TableLogic(value = "0",delval = "1")
    private Integer delFlag;

    /** 单信息 */
    @TableField(exist = false)
    private List<DemoStudent> demoStudentList;

}
