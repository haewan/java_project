package com.springboot.jagent.main;

import ch.qos.logback.classic.Logger;
import com.springboot.jagent.controller.Shellcontroller;
import com.springboot.jagent.domain.Jagentconfig;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

@ComponentScan("com.springboot.jagent.*")
@SpringBootApplication
//@EnableConfigurationProperties(Jagentconfig.class)
public class Application
{
    private static final Logger log = (Logger) LoggerFactory.getLogger(Application.class);

    public static void main(String[] args)
    {
          ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
          Jagentconfig jc = ctx.getBean(Jagentconfig.class);
          Shellcontroller sc = new Shellcontroller(jc);
    }

}