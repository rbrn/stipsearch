package com.db.pwcc.tre.elastic.config;

import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.SpringDataMongo3Driver;
import com.github.cloudyrock.spring.MongockSpring5;
import com.github.cloudyrock.spring.MongockSpring5.MongockInitializingBeanRunner;

import io.github.jhipster.config.JHipsterConstants;
import io.github.jhipster.domain.util.JSR310DateConverters.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableMongoRepositories("com.db.pwcc.tre.elastic.repository")
@Profile(JHipsterConstants.SPRING_PROFILE_CLOUD)
public class CloudDatabaseConfiguration extends AbstractCloudConfig {

    private final Logger log = LoggerFactory.getLogger(CloudDatabaseConfiguration.class);

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(DateToZonedDateTimeConverter.INSTANCE);
        converterList.add(ZonedDateTimeToDateConverter.INSTANCE);
        converterList.add(DurationToLongConverter.INSTANCE);
        return new MongoCustomConversions(converterList);
    }

    @Bean
    public MongockInitializingBeanRunner mongockInitializingBeanRunner(ApplicationContext springContext,
              MongoTemplate mongoTemplate,
              @Value("${mongock.lock.lockAcquiredForMinutes:5}") long lockAcquiredForMinutes,
              @Value("${mongock.lock.maxWaitingForLockMinutes:3}") long maxWaitingForLockMinutes,
              @Value("${mongock.lock.maxTries:3}") int maxTries) {
        return null;
    }
}
