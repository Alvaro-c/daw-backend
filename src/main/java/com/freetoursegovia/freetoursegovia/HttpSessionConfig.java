package com.freetoursegovia.freetoursegovia;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * Created by Java Developer Zone on 13-11-2017.
 */
@Configuration
@EnableJdbcHttpSession
public class HttpSessionConfig extends AbstractHttpSessionApplicationInitializer {
    @Bean
    public HttpSessionStrategy httpSessionStrategy() {      // maintain session using header (mostly for rest session management)
        return new HeaderHttpSessionStrategy();
    }
}