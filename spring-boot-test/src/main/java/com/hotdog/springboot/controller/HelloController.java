package com.hotdog.springboot.controller;

import com.hotdog.springboot.inf.IMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by hotdog on 2017/3/24.
 */
@Controller
public class HelloController {

    @Resource
    private IMessageService messageService;

    @RequestMapping("/message")
    public String message(Model model){
        model.addAttribute("messages", messageService.findMessageInfo());
        return "message";
    }

    @RequestMapping("/error500")
    public void index() {
        int a = 1 / 0; System.out.println(a);
    }

    @RequestMapping("/error400/{id}")
    public Object error400(@PathVariable("id") Integer id) {
        System.out.println(id); return id;
    }

}
