package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ProductMapper;
import com.ruoyi.system.domain.Product;
import com.ruoyi.system.service.IProductService;

/**
 * 产品Service业务层处理
 *
 * @author iuuani
 * @date 2024-04-19
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询产品
     *
     * @param id 产品主键
     * @return 产品
     */
    @Override
    public Product selectProductById(Long id) {
        return productMapper.selectProductById(id);
    }

    /**
     * 查询产品列表
     *
     * @param product 产品
     * @return 产品
     */
    @Override
    public List<Product> selectProductList(Product product) {
        return productMapper.selectProductList(product);
    }

    /**
     * 新增产品
     *
     * @param product 产品
     * @return 结果
     */
    @Override
    public int insertProduct(Product product) {
        return productMapper.insertProduct(product);
    }

    /**
     * 修改产品
     *
     * @param product 产品
     * @return 结果
     */
    @Override
    public int updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }

    /**
     * 批量删除产品
     *
     * @param ids 需要删除的产品主键
     * @return 结果
     */
    @Override
    public int deleteProductByIds(Long[] ids) {
        return productMapper.deleteProductByIds(ids);
    }

    /**
     * 删除产品信息
     *
     * @param id 产品主键
     * @return 结果
     */
    @Override
    public int deleteProductById(Long id) {
        return productMapper.deleteProductById(id);
    }
}
