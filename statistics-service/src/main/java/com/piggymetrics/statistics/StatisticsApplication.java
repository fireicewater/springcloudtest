package com.piggymetrics.statistics;

import com.piggymetrics.statistics.repository.converter.DataPointIdReaderConverter;
import com.piggymetrics.statistics.repository.converter.DataPointIdWriterConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableFeignClients
public class StatisticsApplication {


    @Autowired
//	TODO 回滚
    private RestTemplateBuilder builder;


    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class, args);
    }

    @Bean
    //	TODO 回滚
    public RestTemplate restTemplate() {
        return builder.build();
    }


    @Configuration
    static class CustomConversionsConfig {

        @Bean
        public CustomConversions customConversions() {
            return new CustomConversions(Arrays.asList(new DataPointIdReaderConverter(),
                    new DataPointIdWriterConverter()));
        }
    }
}
