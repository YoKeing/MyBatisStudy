package com.mybatis.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatis.bean.Employee;
import com.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

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
            PageHelper.startPage(1, 5);
            List<Employee> emps = mapper.getEmps();
            PageInfo<Employee> info = new PageInfo<>(emps);

            for(Employee employee : emps){
                System.out.println(employee);
            }
            System.out.println(info.getPageNum());
            System.out.println(info.getTotal());
            System.out.println(info.getPageSize());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testBatch() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession(ExecutorType.BATCH);

        try{
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            for(int i = 0; i < 1000; i++){
                mapper.addEmp(new Employee(UUID.randomUUID().toString().substring(0, 5), "b", "1"));
            }

            openSession.commit();

        }finally {
            openSession.close();
        }



    }


}




































