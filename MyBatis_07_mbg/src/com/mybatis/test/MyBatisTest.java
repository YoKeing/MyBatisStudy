package com.mybatis.test;

import com.mybatis.bean.Employee;
import com.mybatis.bean.EmployeeExample;
import com.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Abyss
 */
public class MyBatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testMbg() throws Exception {

        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        File configFile = new File("mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

//    @Test
//    public void testSimple() throws IOException {
//
//        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//        SqlSession openSession = sqlSessionFactory.openSession();
//
//        try{
//            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
//            List<Employee> list = mapper.selectAll();
//
//            for(Employee employee : list){
//                System.out.println(employee);
//            }
//
//        }finally {
//            openSession.close();
//        }
//
//    }

    @Test
    public void testMyBatis3() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);


            EmployeeExample example = new EmployeeExample();
            EmployeeExample.Criteria criteria = example.createCriteria();
            criteria.andLastNameLike("%e%");
            criteria.andGenderEqualTo("1");

            EmployeeExample.Criteria criteria2 = example.createCriteria();
            criteria2.andEmailLike("%e%");
            example.or(criteria2);

            List<Employee> list = mapper.selectByExample(example);
            for (Employee emp : list){
                System.out.println(emp);
            }

        } finally {
            openSession.close();
        }

    }


}








































