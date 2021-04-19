package com.mybatis.test;

import com.mybatis.bean.Department;
import com.mybatis.bean.Employee;
import com.mybatis.dao.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Abyss
 */
public class MyBatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resoure = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(resoure);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testFirstLevelCache() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try{

            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee empById = mapper.getEmpById(1);
            System.out.println(empById);

        }finally {
            openSession.close();
        }

    }

    @Test
    public void testSecondLevelCache() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        SqlSession openSession1 = sqlSessionFactory.openSession();

        try{

            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper1 = openSession1.getMapper(EmployeeMapper.class);

            Employee empById = mapper.getEmpById(1);
            openSession.close();
            Employee empById1 = mapper1.getEmpById(1);
            openSession1.close();

            System.out.println(empById);
            System.out.println(empById1);
        }finally {
        }

        }


}








































