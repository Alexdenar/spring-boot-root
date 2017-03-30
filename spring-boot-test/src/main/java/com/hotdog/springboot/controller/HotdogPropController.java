package com.hotdog.springboot.controller;

import com.hotdog.springboot.common.util.ResultMsg;
import com.hotdog.springboot.common.util.ResultStatusCode;
import com.hotdog.springboot.model.customProp.Audience;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hotdog on 2017/3/30.
 */
@RestController
public class HotdogPropController {

    @Autowired
    private Audience hotdogProp;

    @RequestMapping(value = "/getHotdogProp", method = RequestMethod.GET)
    @ApiOperation(value = "getHotdogProp", notes = "获取自定properties")
    public Object getHotdogProp(){
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), hotdogProp);
        return resultMsg;
    }
}
