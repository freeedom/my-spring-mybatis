package org.example.springMybatisIntergration;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class MybatisRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableMyBatis.class.getName());
        String mapperScan = (String) annotationAttributes.get("mapperScan");
        MyBatisScanner scanner = new MyBatisScanner(registry);
        scanner.addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                return true;
            }
        });
        Set<BeanDefinitionHolder> candidateComponents = scanner.doScan(mapperScan);
        for(BeanDefinitionHolder bdh:candidateComponents){
            BeanDefinition bd = bdh.getBeanDefinition();
            String mapperClass = bd.getBeanClassName();
            bd.setBeanClassName(MybatisFactoryBean.class.getName());
            bd.getPropertyValues().addPropertyValue("mapperClass",mapperClass);
        }

    }
}
