package com.hotdog.springboot.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hotdog on 2017/3/22.
 */
@RestController
public class SampleController {

    @RequestMapping(value="/", method= RequestMethod.GET)
    @ApiOperation(value="这是一个初始化页面", notes="")
    public String index(){
        return "hotdog:Spring Boot Application...";
    }

    @RequestMapping(value = "/test/{val}", method= RequestMethod.GET)
    @ApiOperation(value="这是一个测试Restful", notes="根据所传参数，返回结果")
    @ApiImplicitParam(name = "val", value = "String类型", required = true, dataType = "String",paramType = "path")
    public String test(@PathVariable("val") String val) {
        return "Hello! "+val;
    }

}
