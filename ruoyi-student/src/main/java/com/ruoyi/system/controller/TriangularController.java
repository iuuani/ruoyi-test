package com.ruoyi.system.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.CorrectedTriangle;
import com.ruoyi.system.domain.Triangle;
import com.ruoyi.system.domain.TriangleLite;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: TriangularController
 * Package: com.ruoyi.system.controller
 * Description:
 *
 * @Author iuuani
 * @Create 2024/3/25 12:39
 * @Version 1.0
 */
@RestController
@RequestMapping("/triangular")
public class TriangularController extends BaseController {
    @PostMapping
    public AjaxResult triangular(@RequestBody TriangleLite lite) {
        try {
            Triangle triangle = new Triangle((int) lite.getSideA(), (int) lite.getSideB(), (int) lite.getSideC());
            Map data = new HashMap();
            data.put("type", triangle.toString());
            data.put("area", triangle.getArea());
            data.put("circumference", triangle.getCircumference());
            return AjaxResult.success(data);
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }

    }

}

