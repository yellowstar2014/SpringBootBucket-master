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
     * 主页
     *
     * @param model
     * @return
     */
    @RequestMapping(value="/testInsert", method = RequestMethod.GET)
    @ResponseBody
    public String index(Model model) {
        User user = new User();
        user.setUsername("xiaoxx2");
        user.setName("小星星2");
        user.setPassword("222222");
        user.setPhone("13890907676");
        userService.insertUser(user);
        return "success";
    }

    @RequestMapping(value="/testUpdate", method = RequestMethod.GET)
    @ResponseBody
    public String index2(Model model) {
        User user1 = userService.findById(9);
        user1.setPassword("888888");
        userService.updateUser(user1);
        return "success";
    }

    @RequestMapping({"/testDelete"})
    @ResponseBody
    public String index3(Model model) {

        userService.deleteUser(9);
        return "success";
    }


}
