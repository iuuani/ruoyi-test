package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.StuHobbies;

/**
 * 学生爱好Service接口
 * 
 * @author ruoyi
 * @date 2024-03-24
 */
public interface IStuHobbiesService 
{
    /**
     * 查询学生爱好
     * 
     * @param id 学生爱好主键
     * @return 学生爱好
     */
    public StuHobbies selectStuHobbiesById(Long id);

    /**
     * 查询学生爱好列表
     * 
     * @param stuHobbies 学生爱好
     * @return 学生爱好集合
     */
    public List<StuHobbies> selectStuHobbiesList(StuHobbies stuHobbies);

    /**
     * 新增学生爱好
     * 
     * @param stuHobbies 学生爱好
     * @return 结果
     */
    public int insertStuHobbies(StuHobbies stuHobbies);

    /**
     * 修改学生爱好
     * 
     * @param stuHobbies 学生爱好
     * @return 结果
     */
    public int updateStuHobbies(StuHobbies stuHobbies);

    /**
     * 批量删除学生爱好
     * 
     * @param ids 需要删除的学生爱好主键集合
     * @return 结果
     */
    public int deleteStuHobbiesByIds(Long[] ids);

    /**
     * 删除学生爱好信息
     * 
     * @param id 学生爱好主键
     * @return 结果
     */
    public int deleteStuHobbiesById(Long id);
}
