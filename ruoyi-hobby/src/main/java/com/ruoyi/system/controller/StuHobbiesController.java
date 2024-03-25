package com.ruoyi.system.controller;

import java.util.List;
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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.config.enums.BusinessType;
import com.ruoyi.system.domain.StuHobbies;
import com.ruoyi.system.service.IStuHobbiesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生爱好Controller
 * 
 * @author ruoyi
 * @date 2024-03-24
 */
@RestController
@RequestMapping("/test/hobbies")
public class StuHobbiesController extends BaseController
{
    @Autowired
    private IStuHobbiesService stuHobbiesService;

    /**
     * 查询学生爱好列表
     */
    @PreAuthorize("@ss.hasPermi('test:hobbies:list')")
    @GetMapping("/list")
    public TableDataInfo list(StuHobbies stuHobbies)
    {
        startPage();
        List<StuHobbies> list = stuHobbiesService.selectStuHobbiesList(stuHobbies);
        return getDataTable(list);
    }

    /**
     * 导出学生爱好列表
     */
    @PreAuthorize("@ss.hasPermi('test:hobbies:export')")
    @Log(title = "学生爱好", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StuHobbies stuHobbies)
    {
        List<StuHobbies> list = stuHobbiesService.selectStuHobbiesList(stuHobbies);
        ExcelUtil<StuHobbies> util = new ExcelUtil<StuHobbies>(StuHobbies.class);
        util.exportExcel(response, list, "学生爱好数据");
    }

    /**
     * 获取学生爱好详细信息
     */
    @PreAuthorize("@ss.hasPermi('test:hobbies:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(stuHobbiesService.selectStuHobbiesById(id));
    }

    /**
     * 新增学生爱好
     */
    @PreAuthorize("@ss.hasPermi('test:hobbies:add')")
    @Log(title = "学生爱好", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StuHobbies stuHobbies)
    {
        return toAjax(stuHobbiesService.insertStuHobbies(stuHobbies));
    }

    /**
     * 修改学生爱好
     */
    @PreAuthorize("@ss.hasPermi('test:hobbies:edit')")
    @Log(title = "学生爱好", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StuHobbies stuHobbies)
    {
        return toAjax(stuHobbiesService.updateStuHobbies(stuHobbies));
    }

    /**
     * 删除学生爱好
     */
    @PreAuthorize("@ss.hasPermi('test:hobbies:remove')")
    @Log(title = "学生爱好", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(stuHobbiesService.deleteStuHobbiesByIds(ids));
    }
}
