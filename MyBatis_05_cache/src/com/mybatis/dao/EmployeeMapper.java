package com.mybatis.dao;

import com.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

//    @Select("select * from tbl_employee where last_name like #{lastName}")
    public List<Employee> getEmpsByLastNameLike(String lastName);

//    @Select("select * from tbl_employee where id = #{id}")
    public Employee getEmpById(Integer id);

//    @Insert("insert into tbl_employee(last_name, email, gender)\n" +
//            "        values(#{lastName}, #{email}, #{gender})")
    public Long addEmp(Employee employee);

//    @Update("update tbl_employee\n" +
//            "        set last_name = #{lastName}, email = #{email}, gender = #{gender}\n" +
//            "        where id = #{id}")
    public Boolean updateEmp(Employee employee);

//    @Delete("delete from tbl_employee where id = #{id}")
    public void deleteEmpById(Integer id);

//    @Select("select * from tbl_employee where id = #{id}")
    public Map<String, Object> getEmpByIdReturnMap(Integer id);


    @MapKey("id")
//    @Select("select * from tbl_employee where last_name like #{lastName}")
    public Map<Integer, Employee> getEmpByLastNameLikeReturnMap(String lastName);

}
