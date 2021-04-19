package com.mybatis.dao;

import com.mybatis.bean.Employee;

import java.util.List;

public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public List<Employee> getEmps();

    public Long addEmp(Employee employee);

}
