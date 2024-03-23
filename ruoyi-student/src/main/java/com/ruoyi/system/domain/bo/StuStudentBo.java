package com.ruoyi.system.domain.bo;

import com.ruoyi.system.domain.StuStudent;

import java.util.List;

/**
 * ClassName: StuStudentBo
 * Package: com.ruoyi.system.domain.bo
 * Description:
 *
 * @Author iuuani
 * @Create 2024/3/17 19:38
 * @Version 1.0
 */
public class StuStudentBo {
    /**
     * 方法1 使用Bo对象处理
     *
     * @param studentList 原学生信息集合
     * @return 修改后的学生信息集合
     */
    public List<StuStudent> updateStuStudent(List<StuStudent> studentList) {
        for (StuStudent student : studentList) {
            if (student.getStudentSex() != null) {
                if (student.getStudentSex().equals("1")) {
                    student.setStudentSex("男");
                } else {
                    student.setStudentSex("女");
                }
            }
        }
        return studentList;
    }
}
