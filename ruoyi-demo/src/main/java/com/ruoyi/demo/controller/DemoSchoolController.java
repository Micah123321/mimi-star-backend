package com.ruoyi.demo.controller;

import java.util.List;
import java.util.Arrays;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.demo.domain.DemoSchool;
import com.ruoyi.demo.service.IDemoSchoolService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 树Controller
 *
 * @author Micah
 * @date 2023-07-23
 */
@RestController
@RequestMapping("/demo/school")
public class DemoSchoolController extends BaseController {
    @Autowired
    private IDemoSchoolService demoSchoolService;

    /**
     * 查询树列表
     */
    @PreAuthorize("@ss.hasPermi('demo:school:list')")
    @GetMapping("/list")
    public AjaxResult list(DemoSchool demoSchool) {
        List<DemoSchool> list = demoSchoolService.selectDemoSchoolList(demoSchool);
        return success(list);
    }

    /**
     * 导出树列表
     */
    @PreAuthorize("@ss.hasPermi('demo:school:export')")
    @Log(title = "树", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DemoSchool demoSchool) {
        List<DemoSchool> list = demoSchoolService.selectDemoSchoolList(demoSchool);
        ExcelUtil<DemoSchool> util = new ExcelUtil<DemoSchool>(DemoSchool.class);
        util.exportExcel(response, list, "树数据");
    }

    /**
     * 获取树详细信息
     */
    @PreAuthorize("@ss.hasPermi('demo:school:query')")
    @GetMapping(value = "/{schoolId}")
    public AjaxResult getInfo(@PathVariable("schoolId") Long schoolId) {
        return success(demoSchoolService.getById(schoolId));
    }

    /**
     * 新增树
     */
    @PreAuthorize("@ss.hasPermi('demo:school:add')")
    @Log(title = "树", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DemoSchool demoSchool) {
        return toAjax(demoSchoolService.save(demoSchool));
    }

    /**
     * 修改树
     */
    @PreAuthorize("@ss.hasPermi('demo:school:edit')")
    @Log(title = "树", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DemoSchool demoSchool) {
        return toAjax(demoSchoolService.updateById(demoSchool));
    }

    /**
     * 删除树
     */
    @PreAuthorize("@ss.hasPermi('demo:school:remove')")
    @Log(title = "树", businessType = BusinessType.DELETE)
	@DeleteMapping("/{schoolIds}")
    public AjaxResult remove(@PathVariable Long[] schoolIds) {
        return toAjax(demoSchoolService.removeByIds(Arrays.asList(schoolIds)));
    }
}
