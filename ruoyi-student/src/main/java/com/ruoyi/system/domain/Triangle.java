package com.ruoyi.system.domain;

/**
 * 三角形类
 */
public class Triangle {
    /**
     * 三角形类型
     */

    private TriangleType type;
    /**
     * 三角形边长A
     */
    private int sideA;
    /**
     * 三角形边长B
     */
    private int sideB;
    /**
     * 三角形边长C
     */
    private int sideC;

    /**
     * 三角形类型枚举类型
     */
    public enum TriangleType {

        /**
         * 等边三角形
         */
        Equilateral,
        /**
         * 等腰三角形
         */
        Isosceles,

        /**
         * 直角三角形
         */
        RightAngled,

        /**
         * 一般三角形
         */
        General,

        /**
         * 无法构成三角形
         */
        NoTriangle,

        /**
         * 无效三角形
         */
        NoValidTriangle

    }

    public TriangleType getType() {
        if (this.sideA == this.sideB && this.sideA == this.sideC)
            return TriangleType.Equilateral;
        if (this.sideA * this.sideA == this.sideB * this.sideB + this.sideC * this.sideC
                || this.sideB * this.sideB == this.sideA * this.sideA + this.sideC * this.sideC
                || this.sideC * this.sideC == this.sideA * this.sideA + this.sideB * this.sideB)
            return TriangleType.RightAngled;

        if (this.sideA + this.sideB > this.sideC
                || this.sideA + this.sideC > this.sideB
                || this.sideB + this.sideC > this.sideA) {
            if (this.sideA == this.sideB || this.sideB == this.sideC || this.sideA == this.sideC)
                return TriangleType.Isosceles;
            else
                return TriangleType.General;
        } else
            return TriangleType.NoTriangle;
    }

    /**
     * 计算三角形面积
     */

    public double getArea() throws Exception {
        if (this.type != TriangleType.NoTriangle && this.type != TriangleType.NoValidTriangle) {
            float s = getCircumference() / 2f;
            return Math.sqrt(s * (s - this.sideA) * (s - this.sideB) * (s - this.sideC));
        } else {
            throw new Exception("无法计算");
        }
    }

    /**
     * 计算三角形周长
     */
    public int getCircumference() {
        return this.sideA + this.sideB + this.sideC;
    }

    public Triangle(Integer a, Integer b, Integer c) throws Exception {
        if (a > 100 || a < 1 || b > 100 || b < 1 || c > 100 || c < 1)
            throw new Exception("三角形边长不在指定范围之类");
        this.sideA = a;
        this.sideB = b;
        this.sideC = c;
        this.type = this.getType();
    }

}



