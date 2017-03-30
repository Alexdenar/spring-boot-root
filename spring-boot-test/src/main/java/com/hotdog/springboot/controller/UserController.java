package com.hotdog.springboot.controller;

import com.hotdog.springboot.common.util.ResultMsg;
import com.hotdog.springboot.common.util.ResultStatusCode;
import com.hotdog.springboot.mapper.UserRepositoy;
import com.hotdog.springboot.model.mybatis.UserInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hotdog on 2017/3/30.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepositoy userRepositoy;

    @ApiOperation(value = "查询用户列表",notes = "")
    @RequestMapping(value = "/findUsers",method = RequestMethod.GET)
    public Object findUsers(){
        List<UserInfo> list = userRepositoy.findUsers();
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), list);
        return resultMsg;
    }

    @RequestMapping(value = "/findUserByName/{name}",method = RequestMethod.GET)
    public Object findUserByName(@PathVariable String name){
        UserInfo userInfo = userRepositoy.findUserInfoByName(name);
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userInfo);
        return resultMsg;
    }

}
