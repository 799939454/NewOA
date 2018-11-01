package com.wise.newoa.config;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.net.UnknownHostException;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.primary.uri}")
    private String MONGO_URI1;


    @Value("${spring.data.mongodb.secondary.uri}")
    private String MONGO_URI2;


    @Bean
    public MongoMappingContext mongoMappingContext() {
        MongoMappingContext mappingContext = new MongoMappingContext();
        return mappingContext;
    }


    @Bean //使用自定义的typeMapper去除写入mongodb时的“_class”字段
    public MappingMongoConverter mappingMongoConverter1() throws Exception {
        DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(this.dbFactory1());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, this.mongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }



    @Bean
    @Primary
    public MongoDbFactory dbFactory1() throws UnknownHostException {
        return new SimpleMongoDbFactory(new MongoClientURI(MONGO_URI1));
    }

    @Bean
    @Primary
    public MongoTemplate mongoTemplate1() throws Exception {
        return new MongoTemplate(this.dbFactory1(), this.mappingMongoConverter1());
    }



    @Bean
    public MongoDbFactory dbFactory2() throws UnknownHostException {
        return new SimpleMongoDbFactory(new MongoClientURI(MONGO_URI2));
    }

    @Bean
    public MongoTemplate mongoTemplate2() throws Exception {
        return new MongoTemplate(dbFactory2());
    }


}
