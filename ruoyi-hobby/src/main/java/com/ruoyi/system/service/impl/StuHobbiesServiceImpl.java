package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StuHobbiesMapper;
import com.ruoyi.system.domain.StuHobbies;
import com.ruoyi.system.service.IStuHobbiesService;

/**
 * 学生爱好Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-24
 */
@Service
public class StuHobbiesServiceImpl implements IStuHobbiesService {
    @Autowired
    private StuHobbiesMapper stuHobbiesMapper;

    /**
     * 查询学生爱好
     *
     * @param id 学生爱好主键
     * @return 学生爱好
     */
    @Override
    public StuHobbies selectStuHobbiesById(Long id) {
        return stuHobbiesMapper.selectStuHobbiesById(id);
    }

    /**
     * 查询学生爱好列表
     *
     * @param stuHobbies 学生爱好
     * @return 学生爱好
     */
    @Override
    public List<StuHobbies> selectStuHobbiesList(StuHobbies stuHobbies) {
        return stuHobbiesMapper.selectStuHobbiesList(stuHobbies);
    }

    /**
     * 新增学生爱好
     *
     * @param stuHobbies 学生爱好
     * @return 结果
     */
    @Override
    public int insertStuHobbies(StuHobbies stuHobbies) {
        try {
            return stuHobbiesMapper.insertStuHobbies(stuHobbies);
        } catch (Exception e) {
            // 捕获异常，处理重复插入情况
            e.printStackTrace(); // 在实际环境中应该记录日志并返回错误信息给调用者
            return -1; // 返回特定的错误码或抛出自定义异常
        }
    }

    /**
     * 修改学生爱好
     *
     * @param stuHobbies 学生爱好
     * @return 结果
     */
    @Override
    public int updateStuHobbies(StuHobbies stuHobbies) {
        return stuHobbiesMapper.updateStuHobbies(stuHobbies);
    }

    /**
     * 批量删除学生爱好
     *
     * @param ids 需要删除的学生爱好主键
     * @return 结果
     */
    @Override
    public int deleteStuHobbiesByIds(Long[] ids) {
        return stuHobbiesMapper.deleteStuHobbiesByIds(ids);
    }

    /**
     * 删除学生爱好信息
     *
     * @param id 学生爱好主键
     * @return 结果
     */
    @Override
    public int deleteStuHobbiesById(Long id) {
        return stuHobbiesMapper.deleteStuHobbiesById(id);
    }
}
