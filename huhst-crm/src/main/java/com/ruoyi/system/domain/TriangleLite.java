package com.ruoyi.system.domain;


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

@Validated
public class TriangleLite {

    @NotEmpty
    private double sideA;
    private double sideB;
    private double sideC;

    public double getSideA() {
        return sideA;
    }

    public void setSideA(double sideA) {
        this.sideA = sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public void setSideB(double sideB) {
        this.sideB = sideB;
    }

    public double getSideC() {
        return sideC;
    }

    public void setSideC(double sideC) {
        this.sideC = sideC;
    }
}
