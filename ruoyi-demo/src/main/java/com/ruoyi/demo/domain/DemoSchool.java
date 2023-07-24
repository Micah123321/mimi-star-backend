package com.ruoyi.demo.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreePlusEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 树对象 demo_school
 *
 * @author Micah
 * @date 2023-07-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "demo_school")
public class DemoSchool extends TreePlusEntity {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @TableId(type = IdType.AUTO)
    private Long schoolId;

    /** 学生名称 */
    @Excel(name = "学生名称")
    private String schoolName;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0逻辑未删除值 1逻辑已删除值） */
    @TableLogic(value = "0",delval = "1")
    private Integer delFlag;

}
