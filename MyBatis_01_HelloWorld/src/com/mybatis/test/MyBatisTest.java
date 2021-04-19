package com.mybatis.test;

import com.mybatis.bean.Employee;
import com.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

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

}
