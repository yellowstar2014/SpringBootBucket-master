package com.xncoding.pos.controller;

import com.xncoding.pos.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * AfterStartRunner
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/9/15
 */
@Controller
@RequestMapping(value="/")
public class TestController {
    @Autowired
    private SenderService senderService;

    @RequestMapping(value="/index2", method = RequestMethod.GET)
    @ResponseBody
    public String index2(){
        System.out.println("--------------start-------------");
        // 测试广播模式
        senderService.broadcast("AfterStartRunner --> 同学们集合啦！");

        // 测试Direct模式
        senderService.direct("AfterStartRunner --> driect定点消息");
        int i=0;
        while (true) {//为了验证exchange和queu持久化,消息不丢失
            // 测试Top模式
            Map<String, String> map = new HashMap<String, String>();
            map.put("kkk"+i, "555666llll");
            senderService.top(map);
            i++;
            if (i==10) break;
        }
        return "success!!!";
    }

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

}
