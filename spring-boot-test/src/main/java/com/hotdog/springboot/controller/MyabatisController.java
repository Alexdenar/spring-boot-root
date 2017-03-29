package com.hotdog.springboot.controller;

import com.github.pagehelper.PageHelper;
import com.hotdog.springboot.inf.IMessageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hotdog on 2017/3/24.
 */
@Controller
public class MyabatisController {

    @Autowired
    private IMessageService messageService;

    @ApiOperation(value="message分页查询", notes="根据currentPage、pageSize返回查询结果")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", paramType = "path", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", paramType = "path", value = "每页的数据量", required = true, dataType = "Integer")
    })
    @RequestMapping(value="/pagequery/{currentPage}/{pageSize}",method= RequestMethod.GET)
    public String index(Model model, @PathVariable("currentPage") Integer currentPage,
                        @PathVariable("pageSize") Integer pageSize) {
        if (currentPage != null) {
            PageHelper.startPage(currentPage, pageSize);
        }
        model.addAttribute("list", messageService.findMessageInfo());
        int total = messageService.count();
        model.addAttribute("totalPage", total / pageSize + (total % pageSize > 0 ? 1 : 0));
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", currentPage);
        return "pagequery";
    }
}
