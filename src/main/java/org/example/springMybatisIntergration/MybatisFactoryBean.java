package org.example.springMybatisIntergration;

import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;

@Data
public class MybatisFactoryBean implements FactoryBean, BeanFactoryAware {

    private String mapperClass;

    private BeanFactory beanFactory;

    @Override
    public Object getObject() throws Exception {
        SqlSessionFactory sqlSessionFactory = beanFactory.getBean(SqlSessionFactory.class);
        return sqlSessionFactory.openSession().getMapper(getObjectType());
    }

    @Override
    public Class<?> getObjectType() {
        if(mapperClass == null){
            return null;
        }
        try {
            return getClass().getClassLoader().loadClass(mapperClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
