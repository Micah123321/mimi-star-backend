package com.ruoyi.demo.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BasePlusEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 单对象 demo_student
 *
 * @author Micah
 * @date 2023-07-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "demo_student")
public class DemoStudent extends BasePlusEntity {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @TableId(type = IdType.AUTO)
    private Long studentId;

    /** 所属班级ID */
    @Excel(name = "所属班级ID")
    private Long classesId;

    /** 学生名称 */
    @Excel(name = "学生名称")
    private String studentName;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long studentAge;

    /** 性别（0男 1女 2未知） */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String studentSex;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date studentBirthday;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String STATUS;

    /** 删除标志（0逻辑未删除值 1逻辑已删除值） */
    @TableLogic(value = "0",delval = "1")
    private Integer delFlag;

}
