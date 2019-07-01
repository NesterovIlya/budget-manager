package com.github.nesterovilya.budgetmanager.integration.dao;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @author Ilya Nesterov
 */

@TestConfiguration
public class DaoTestConfig {

    @Bean
    public ObjectMapper ymlObjectMapper() {
        YAMLFactory yamlFactory = new YAMLFactory();
        yamlFactory.enable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
        ObjectMapper objectMapper = new ObjectMapper(yamlFactory);
        //objectMapper.setPropertyNamingStrategy(new CustomNamingStrategy());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        objectMapper.setDateFormat(sdf);
        return objectMapper;
    }
}
