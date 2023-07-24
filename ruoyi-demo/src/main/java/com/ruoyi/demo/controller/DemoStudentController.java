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
import com.ruoyi.demo.domain.DemoStudent;
import com.ruoyi.demo.service.IDemoStudentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 单Controller
 *
 * @author Micah
 * @date 2023-07-23
 */
@RestController
@RequestMapping("/demo/student")
public class DemoStudentController extends BaseController {
    @Autowired
    private IDemoStudentService demoStudentService;

    /**
     * 查询单列表
     */
    @PreAuthorize("@ss.hasPermi('demo:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(DemoStudent demoStudent) {
        startPage();
        List<DemoStudent> list = demoStudentService.selectDemoStudentList(demoStudent);
        return getDataTable(list);
    }

    /**
     * 导出单列表
     */
    @PreAuthorize("@ss.hasPermi('demo:student:export')")
    @Log(title = "单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DemoStudent demoStudent) {
        List<DemoStudent> list = demoStudentService.selectDemoStudentList(demoStudent);
        ExcelUtil<DemoStudent> util = new ExcelUtil<DemoStudent>(DemoStudent.class);
        util.exportExcel(response, list, "单数据");
    }

    /**
     * 获取单详细信息
     */
    @PreAuthorize("@ss.hasPermi('demo:student:query')")
    @GetMapping(value = "/{studentId}")
    public AjaxResult getInfo(@PathVariable("studentId") Long studentId) {
        return success(demoStudentService.getById(studentId));
    }

    /**
     * 新增单
     */
    @PreAuthorize("@ss.hasPermi('demo:student:add')")
    @Log(title = "单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DemoStudent demoStudent) {
        return toAjax(demoStudentService.save(demoStudent));
    }

    /**
     * 修改单
     */
    @PreAuthorize("@ss.hasPermi('demo:student:edit')")
    @Log(title = "单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DemoStudent demoStudent) {
        return toAjax(demoStudentService.updateById(demoStudent));
    }

    /**
     * 删除单
     */
    @PreAuthorize("@ss.hasPermi('demo:student:remove')")
    @Log(title = "单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{studentIds}")
    public AjaxResult remove(@PathVariable Long[] studentIds) {
        return toAjax(demoStudentService.removeByIds(Arrays.asList(studentIds)));
    }
}
