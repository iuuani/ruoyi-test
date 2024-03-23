package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.StuStudent;

/**
 * 学生信息Mapper接口
 *
 * @author ruoyi
 * @date 2024-03-16
 */
public interface StuStudentMapper {
    /**
     * 查询学生信息
     *
     * @param studentId 学生信息主键
     * @return 学生信息
     */
    public StuStudent selectStuStudentByStudentId(Long studentId);

    /**
     * 查询学生信息列表
     *
     * @param stuStudent 学生信息
     * @return 学生信息集合
     */
    public List<StuStudent> selectStuStudentList(StuStudent stuStudent);

    /**
     * 新增学生信息
     *
     * @param stuStudent 学生信息
     * @return 结果
     */
    public int insertStuStudent(StuStudent stuStudent);

    /**
     * 修改学生信息
     *
     * @param stuStudent 学生信息
     * @return 结果
     */
    public int updateStuStudent(StuStudent stuStudent);

    /**
     * 删除学生信息
     *
     * @param studentId 学生信息主键
     * @return 结果
     */
    public int deleteStuStudentByStudentId(Long studentId);

    /**
     * 批量删除学生信息
     *
     * @param studentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStuStudentByStudentIds(Long[] studentIds);

    /**
     * 方法2
     * @param stuStudent 学生信息
     */
    void updateStudent(StuStudent stuStudent);
}
