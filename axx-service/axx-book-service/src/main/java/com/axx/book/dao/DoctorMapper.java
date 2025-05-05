package com.axx.book.dao;
import com.axx.book.pojo.Doctor;
import java.util.List;

/****
 * @Author:axx
 * @Description:Doctor的Dao
 * @Date 2021/2/1 14:19
 *****/
public interface DoctorMapper {
    /**
     * 查询所有医生信息
     * @return 医生信息列表
     */
    List<Doctor> selectAll();
    /**
     * 根据示例条件查询医生信息
     * @param doctor 医生信息示例
     * @return 符合条件的医生信息列表
     */
    List<Doctor> selectByExample(Doctor doctor);
    /**
     * 根据医生 ID 查询医生信息
     * @param id 医生 ID
     * @return 对应的医生信息
     */
    Doctor selectById(Integer id);
    /**
     * 插入新的医生信息
     * @param doctor 要插入的医生信息
     * @return 插入操作影响的行数
     */
    int insert(Doctor doctor);
    /**
     * 更新医生信息
     * @param doctor 要更新的医生信息
     * @return 更新操作影响的行数
     */
    int update(Doctor doctor);
    /**
     * 根据医生 ID 删除医生信息
     * @param id 医生 ID
     * @return 删除操作影响的行数
     */
    int deleteById(Integer id);
    /**
     * 根据科室 ID 查询该科室的所有医生信息
     * @param departmentId 科室 ID
     * @return 该科室的医生信息列表
     */
    List<Doctor> selectByDepartmentId(Integer departmentId);
}
