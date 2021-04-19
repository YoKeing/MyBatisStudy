package com.mybatis.dao;

import com.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapperAnntation {

    @Select("select * from tbl_employee where id = #{id}")
    public Employee getEmpById(Integer id);

}
