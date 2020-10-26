package com.springboot.jagent.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties("shell")
public class Jagentconfig {

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }
    @Value("${shell.path:not set}")
    String path;
    @Value("${shell.timeout:not set}")
    String timeout;

}
