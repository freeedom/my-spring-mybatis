package org.example;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.ProductMapper;
import org.example.springMybatisIntergration.EnableMyBatis;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.example")
@EnableMyBatis(mapperScan = "org.example.mapper")
public class MySpringMybatisApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MySpringMybatisApplication.class);
        ProductMapper productMapper = applicationContext.getBean(ProductMapper.class);
        Object o = applicationContext.getBean("&productMapper");
        System.out.println(productMapper.selectAll());

    }
}
