package com.mybatis.dao;

import com.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapperDynamicSQL {

    public List<Employee> getEmpsByConditionIf(Employee employee);

    public List<Employee> getEmpsByConditionTrim(Employee employee);

    public List<Employee> getEmpsByConditionChoose(Employee employee);

    public void updateEmp(Employee employee);

    public List<Employee> getEmpsByConditionForeach(List<Integer> ids);

    public void addEmps(@Param("emps") List<Employee> emps);

    public List<Employee> getEmpsTestInnerParamenter(Employee employee);

}
