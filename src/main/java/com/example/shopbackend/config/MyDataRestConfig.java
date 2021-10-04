package com.example.shopbackend.config;

import com.example.shopbackend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//chỉ cho lấy ra chứ ko  cho thêm xóa sửa
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    private EntityManager entityManager;

    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;

    @Autowired
    public  MyDataRestConfig(EntityManager theEntityManager){
        entityManager = theEntityManager;

    }


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        //Config tắt thêm sửa xóa
        HttpMethod[] theUnsupportedActions = {HttpMethod.POST,HttpMethod.DELETE,HttpMethod.PUT,HttpMethod.PATCH};
        disableHttpMethod(Product.class,config,theUnsupportedActions);
        disableHttpMethod(Country.class,config,theUnsupportedActions);
        disableHttpMethod(ProductCategory.class,config,theUnsupportedActions);
        disableHttpMethod(State.class,config,theUnsupportedActions);
        disableHttpMethod(Order.class,config,theUnsupportedActions);
        exposeIds(config);
        cors.addMapping(config.getBasePath()+"/**").allowedOrigins(theAllowedOrigins);
    }

    private void disableHttpMethod(Class cl,RepositoryRestConfiguration config,HttpMethod[] theUnsupportedActions){
        config.getExposureConfiguration().forDomainType(cl)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void  exposeIds(RepositoryRestConfiguration config){
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        List<Class> entityClasses = new ArrayList<>();
        for (EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
