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
import com.ruoyi.demo.domain.DemoClasses;
import com.ruoyi.demo.service.IDemoClassesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 主子Controller
 *
 * @author Micah
 * @date 2023-07-23
 */
@RestController
@RequestMapping("/demo/classes")
public class DemoClassesController extends BaseController {
    @Autowired
    private IDemoClassesService demoClassesService;

    /**
     * 查询主子列表
     */
    @PreAuthorize("@ss.hasPermi('demo:classes:list')")
    @GetMapping("/list")
    public TableDataInfo list(DemoClasses demoClasses) {
        startPage();
        List<DemoClasses> list = demoClassesService.selectDemoClassesList(demoClasses);
        return getDataTable(list);
    }

    /**
     * 导出主子列表
     */
    @PreAuthorize("@ss.hasPermi('demo:classes:export')")
    @Log(title = "主子", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DemoClasses demoClasses) {
        List<DemoClasses> list = demoClassesService.selectDemoClassesList(demoClasses);
        ExcelUtil<DemoClasses> util = new ExcelUtil<DemoClasses>(DemoClasses.class);
        util.exportExcel(response, list, "主子数据");
    }

    /**
     * 获取主子详细信息
     */
    @PreAuthorize("@ss.hasPermi('demo:classes:query')")
    @GetMapping(value = "/{classesId}")
    public AjaxResult getInfo(@PathVariable("classesId") Long classesId) {
        return success(demoClassesService.selectDemoClassesByClassesId(classesId));
    }

    /**
     * 新增主子
     */
    @PreAuthorize("@ss.hasPermi('demo:classes:add')")
    @Log(title = "主子", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DemoClasses demoClasses) {
        return toAjax(demoClassesService.insertDemoClasses(demoClasses));
    }

    /**
     * 修改主子
     */
    @PreAuthorize("@ss.hasPermi('demo:classes:edit')")
    @Log(title = "主子", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DemoClasses demoClasses) {
        return toAjax(demoClassesService.updateDemoClasses(demoClasses));
    }

    /**
     * 删除主子
     */
    @PreAuthorize("@ss.hasPermi('demo:classes:remove')")
    @Log(title = "主子", businessType = BusinessType.DELETE)
	@DeleteMapping("/{classesIds}")
    public AjaxResult remove(@PathVariable Long[] classesIds) {
        return toAjax(demoClassesService.deleteDemoClassesByClassesIds(classesIds));
    }
}
