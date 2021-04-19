package com.mybatis.test;

import com.mybatis.bean.Department;
import com.mybatis.bean.Employee;
import com.mybatis.dao.DepartmentMapper;
import com.mybatis.dao.EmployeeMapper;
import com.mybatis.dao.EmployeeMapperAnntation;
import com.mybatis.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class MyBatisTest {


    @Test
    public void test() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession openSession = sqlSessionFactory.openSession();

        try{
            Employee employee = openSession.selectOne("com.mybatis.EmployeeMapper.selectEmp", 1);

            System.out.println(employee);
        }finally {
            openSession.close();
        }

    }

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resoure = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(resoure);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test01() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Employee empById = mapper.getEmpById(1);
            System.out.println(empById);
        }finally {
            sqlSession.close();
        }



    }


    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapperAnntation mapper = openSession.getMapper(EmployeeMapperAnntation.class);
            Employee empById = mapper.getEmpById(1);
            System.out.println(empById);
        }finally {
            openSession.close();
        }
    }

    @Test
    public void test03() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

//            Employee employee = new Employee(null, "jerry", "jerry@qq.com", "1");
//            mapper.addEmp(employee);

//            Employee employee = new Employee(1, "jerry", "jerry@qq.com", "0");
//
//            mapper.updateEmp(employee);

//            mapper.deleteEmpById(2);

//            openSession.commit();

//            List<Employee> empsByLastNameLike = mapper.getEmpsByLastNameLike("%e%");
//            for (Employee e: empsByLastNameLike) {
//                System.out.println(e);
//            }
//
//            Map<String, Object> map = mapper.getEmpByIdReturnMap(1);
//            System.out.println(map);
            Map<Integer, Employee> map = mapper.getEmpByLastNameLikeReturnMap("%r%");
            System.out.println(map);

        }finally {
            openSession.close();
        }
    }

    @Test
    public void test05() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);


//            Employee empById = mapper.getEmpById(1);
//            System.out.println(empById);

//            Employee empAndDept = mapper.getEmpAndDept(1);
//            System.out.println(empAndDept);
//            System.out.println(empAndDept.getDepartment());

            Employee employee = mapper.getEmpByIdStep(2);
            System.out.println(employee);
//            System.out.println(employee.getDepartment());
            System.out.println(employee.getDepartment());
        }finally {
            openSession.close();
        }
    }

    @Test
    public void test06() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession();

        try{

            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
//            Department department = mapper.getDeptByIdPlus(1);
//            System.out.println(department);
//            System.out.println(department.getEmployees());

            Department deptByIdStep = mapper.getDeptByIdStep(1);
            System.out.println(deptByIdStep);
            System.out.println(deptByIdStep.getEmployees());

        }finally {
            openSession.close();
        }

    }


}
