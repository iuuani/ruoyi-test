package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品对象 product
 *
 * @author iuuani
 * @date 2024-04-19
 */
public class Product extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 产品编号
     */
    private Long id;

    /**
     * 产品编码
     */
    @Excel(name = "产品编码")
    private String prodCode;

    /**
     * 属性编号
     */
    @Excel(name = "属性编号")
    private Integer prodAttr;

    /**
     * 类型编号
     */
    @Excel(name = "类型编号")
    private Integer prodType;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String prodName;

    /**
     * 品牌编号
     */
    @Excel(name = "品牌编号")
    private Integer prodBrand;

    /**
     * 规格型号
     */
    @Excel(name = "规格型号")
    private String prodSpecs;

    /**
     * 单位编号
     */
    @Excel(name = "单位编号")
    private Integer prodUnit;

    /**
     * 成本单价
     */
    @Excel(name = "成本单价")
    private BigDecimal costPrice;

    /**
     * 增值税税率
     */
    @Excel(name = "增值税税率")
    private BigDecimal vatRate;

    /**
     * 销售单价(含税)
     */
    @Excel(name = "销售单价(含税)")
    private BigDecimal inclTaxPrice;

    /**
     * 销售单价(不含税)
     */
    @Excel(name = "销售单价(不含税)")
    private BigDecimal exclTaxPrice;

    /**
     * 税额
     */
    @Excel(name = "税额")
    private BigDecimal taxAmount;

    /**
     * 售价毛利
     */
    @Excel(name = "售价毛利")
    private BigDecimal grossProfit;

    /**
     * 权限编号
     */
    @Excel(name = "权限编号")
    private Integer prodPerm;

    /**
     * 产品图片
     */
    @Excel(name = "产品图片")
    private String prodImg;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdAttr(Integer prodAttr) {
        this.prodAttr = prodAttr;
    }

    public Integer getProdAttr() {
        return prodAttr;
    }

    public void setProdType(Integer prodType) {
        this.prodType = prodType;
    }

    public Integer getProdType() {
        return prodType;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdBrand(Integer prodBrand) {
        this.prodBrand = prodBrand;
    }

    public Integer getProdBrand() {
        return prodBrand;
    }

    public void setProdSpecs(String prodSpecs) {
        this.prodSpecs = prodSpecs;
    }

    public String getProdSpecs() {
        return prodSpecs;
    }

    public void setProdUnit(Integer prodUnit) {
        this.prodUnit = prodUnit;
    }

    public Integer getProdUnit() {
        return prodUnit;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setVatRate(BigDecimal vatRate) {
        this.vatRate = vatRate;
    }

    public BigDecimal getVatRate() {
        return vatRate;
    }

    public void setInclTaxPrice(BigDecimal inclTaxPrice) {
        this.inclTaxPrice = inclTaxPrice;
    }

    public BigDecimal getInclTaxPrice() {
        return inclTaxPrice;
    }

    public void setExclTaxPrice(BigDecimal exclTaxPrice) {
        this.exclTaxPrice = exclTaxPrice;
    }

    public BigDecimal getExclTaxPrice() {
        return exclTaxPrice;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setGrossProfit(BigDecimal grossProfit) {
        this.grossProfit = grossProfit;
    }

    public BigDecimal getGrossProfit() {
        return grossProfit;
    }

    public void setProdPerm(Integer prodPerm) {
        this.prodPerm = prodPerm;
    }

    public Integer getProdPerm() {
        return prodPerm;
    }

    public void setProdImg(String prodImg) {
        this.prodImg = prodImg;
    }

    public String getProdImg() {
        return prodImg;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("prodCode", getProdCode())
                .append("prodAttr", getProdAttr())
                .append("prodType", getProdType())
                .append("prodName", getProdName())
                .append("prodBrand", getProdBrand())
                .append("prodSpecs", getProdSpecs())
                .append("prodUnit", getProdUnit())
                .append("costPrice", getCostPrice())
                .append("vatRate", getVatRate())
                .append("inclTaxPrice", getInclTaxPrice())
                .append("exclTaxPrice", getExclTaxPrice())
                .append("taxAmount", getTaxAmount())
                .append("grossProfit", getGrossProfit())
                .append("prodPerm", getProdPerm())
                .append("prodImg", getProdImg())
                .append("gmtCreate", getGmtCreate())
                .append("gmtModified", getGmtModified())
                .toString();
    }
}
