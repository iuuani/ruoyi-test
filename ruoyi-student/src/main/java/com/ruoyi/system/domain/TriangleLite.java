package com.ruoyi.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * ClassName: sjx
 * Package: com.ruoyi.system.controller
 * Description:
 *
 * @Author iuuani
 * @Create 2024/3/25 20:39
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class TriangleLite {

    @NotEmpty
    private double sideA;
    private double sideB;
    private double sideC;
}
