package com.ruoyi.system.domain;

/**
 * ClassName: Triangular
 * Package: com.ruoyi.system.domain
 * Description:
 *
 * @Author iuuani
 * @Create 2024/3/25 12:52
 * @Version 1.0
 */
public class Triangle {
    // 三角形类型枚举类型
    public enum TriangleType {
        Equilateral, // 等边三角形
        Isosceles, // 等腰三角形
        RightAngled, // 直角三角形
        General, // 一般三角形
        NoTriangle, // 无法构成三角形
        NoValidTriangle // 无效三角形
    }

    // 三边长度
    public int sideA = 1, sideB = 1, sideC = 1;

    // 三角形类型
    public TriangleType Type = TriangleType.Equilateral;

    // 构造函数
    public Triangle(int a, int b, int c) {
        this.sideA = a;
        this.sideB = b;
        this.sideC = c;
        this.Type = this.getType();
    }

    // 转换为字符串
    public String toString() {
        String typeStr = "";
        switch (this.Type) {
            case Equilateral:
                typeStr = "等边三角形";
                break;
            case Isosceles:
                typeStr = "等腰三角形";
                break;
            case RightAngled:
                typeStr = "直角三角形";
                break;
            case General:
                typeStr = "一般三角形";
                break;
            case NoTriangle:
                typeStr = "无法构成三角形";
                break;
            default:
                typeStr = "无效三角形";
                break;
        }
        return typeStr;
    }

    // 判断三角形类型
    public TriangleType getType() {
        if (this.sideA > 100 || this.sideA < 1
                || this.sideB > 100 || this.sideB < 1
                || this.sideC > 100 || this.sideC < 1)
            return TriangleType.NoValidTriangle;
        if (this.sideA == this.sideB && this.sideA == this.sideC)
            return TriangleType.Equilateral;
        if (this.sideA * this.sideA == this.sideB * this.sideB + this.sideC * this.sideC
                || this.sideB * this.sideB == this.sideA * this.sideA + this.sideC * this.sideC
                || this.sideC * this.sideC == this.sideA * this.sideA + this.sideB * this.sideB)
            return TriangleType.RightAngled;
        if (this.sideA + this.sideB > this.sideC || this.sideA + this.sideC > this.sideB
                || this.sideB + this.sideC > this.sideA) {
            if (this.sideA == this.sideB || this.sideB == this.sideC || this.sideA == this.sideC)
                return TriangleType.Isosceles;
            else
                return TriangleType.General;
        } else
            return TriangleType.NoTriangle;
    }

    // 计算三角形面积
    public double area() {
        if (this.Type != TriangleType.NoTriangle && this.Type != TriangleType.NoValidTriangle) {
            float s = getCircumference() / 2f;
            return Math.sqrt(s * (s - this.sideA) * (s - this.sideB) * (s - this.sideC));
        } else {
            return -1;
        }
    }

    // 计算边长
    public int getCircumference() {
        return this.sideA + this.sideB + this.sideC;
    }
}
