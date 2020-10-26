package com.springboot.jagent.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Hello {

    private String value;

    public String hello()
    {
        return  value;
    }

}
