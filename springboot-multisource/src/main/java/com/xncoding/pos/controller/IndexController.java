package com.xncoding.pos.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xncoding.pos.common.dao.entity.User;
import com.xncoding.pos.service.UserService;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class IndexController {

    private static final Logger _logger = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private UserService userService;

    /**
     * 主页
     *
     * @return
     */
    @RequestMapping(value="/index", method = RequestMethod.GET)
    @ResponseBody
    public String index0() {
        return "index";
    }

    /**
     * 查找用户
     *
     * @param model
     * @return
     */
    @RequestMapping(value="/findUser1", method = RequestMethod.GET)
    @ResponseBody
    public String findUser1(int id) {
        User user = userService.findById(id);
        System.out.println("findUser1:"+user.getUsername());

        return "findUser1:"+user.getUsername();
    }

    /**
     * 查找用户
     *
     * @param model
     * @return
     */
    @RequestMapping(value="/findUser2", method = RequestMethod.GET)
    @ResponseBody
    public String findUser2(int id) {
        User user = userService.findById1(id);
        System.out.println("findUser2:"+user.getUsername());

        return "findUser2:"+user.getUsername();
    }



}
