package com.ruoyi.system.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.common.Result;
import com.ruoyi.system.domain.Triangular;
import com.ruoyi.system.util.WebUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
//@RequestMapping("/triangular")
public class TriangularController extends BaseController {
    @PostMapping
    public AjaxResult calculateTriangular(@RequestBody Triangular triangularData) {
        return AjaxResult.success("成功收到三角形数据: " + triangularData);
    }
}

