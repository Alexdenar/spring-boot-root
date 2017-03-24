package com.hotdog.springboot.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hotdog on 2017/3/22.
 */
@RestController
public class SampleController {

    @RequestMapping("/")
    public String index(){
        return "hotdog:Spring Boot Application...";
    }

    @RequestMapping("/test/{val}")
    @ResponseBody
    public String test(@PathVariable("val") String val) {
        return "Hello! "+val;
    }
}
