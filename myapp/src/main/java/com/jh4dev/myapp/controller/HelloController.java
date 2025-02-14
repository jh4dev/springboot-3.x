package com.jh4dev.myapp.controller;

import com.jh4dev.myapp.bean.HelloBean;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/*
* RestController Annotation
*   - Controller + ResponseBody Annotation
*   - Default Response : Json Format
* */
@RestController
public class HelloController {

    private MessageSource messageSource;

    public HelloController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /*
     * Method - GET
     * URI - hello
     * */
    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello, This is JH4DEV";
    }

    @GetMapping(path = "/hello-bean")
    public HelloBean helloBean() {
        //POJO 는 자동으로 JSON 포맷으로 변환됨
        return new HelloBean("Hello, I'm HelloBean Instance");
    }

    /*
    * PathVariable
    * */
    @GetMapping(path = "/hello-bean/path-variable/{name}")
    public HelloBean getHelloBeanByPathVariable(@PathVariable String name) {
        return new HelloBean(String.format("Hello, I'm HelloBean %s Instance", name));
    }

    @GetMapping(path = "/hello-internationalized")
    public String internationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        //POJO 는 자동으로 JSON 포맷으로 변환됨
        return messageSource.getMessage("greeting.message", null, locale);
    }
}
