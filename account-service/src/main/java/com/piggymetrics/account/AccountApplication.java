package com.piggymetrics.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableFeignClients
@EnableConfigurationProperties
@Configuration
@EnableWebSecurity
public class AccountApplication extends WebSecurityConfigurerAdapter {


    @Autowired
//	TODO 回滚
    private RestTemplateBuilder builder;

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }


    @Bean
    //	TODO 回滚
    public RestTemplate restTemplate() {
        return builder.build();
    }

//	@Bean
//	public RequestInterceptor oauth2FeignRequestInterceptor(){
//		return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
//	}

    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll();
    }
}
