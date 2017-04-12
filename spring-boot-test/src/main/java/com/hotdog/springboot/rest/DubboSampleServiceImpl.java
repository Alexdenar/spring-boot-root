package com.hotdog.springboot.rest;

import com.alibaba.dubbo.config.annotation.Service;
import com.hotdog.springboot.api.DubboSampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by hotdog on 2017/4/5.
 */

@Service
@Path("dubbosample")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
//@Api("dubbo服务测试api")
public class DubboSampleServiceImpl implements DubboSampleService {

    private static final Logger logger = LoggerFactory.getLogger(DubboSampleServiceImpl.class);

    @Override
    @GET
    @Path("hello/{val}")
   // @ApiOperation(value="测试",httpMethod="GET",produces=MediaType.APPLICATION_JSON,notes="传JSON格式")
    //@ApiImplicitParam(name = "val", value = "String类型", required = true, dataType = "String",paramType = "path")
    public String hello (@PathParam("val") String val) {
        return "Hello! "+val;
    }
}
