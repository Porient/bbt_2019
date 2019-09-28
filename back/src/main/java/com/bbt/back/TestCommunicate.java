package com.bbt.back;

import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: 潘浩霖
 * @Date: 2019/2/23
 */
@RestController
public class TestCommunicate {
    @RequestMapping(value = "/testForm",method= RequestMethod.POST)
    @ResponseBody
    public Object testForm(){return "表单提交成功";}

    @RequestMapping(value = "/testJson", method=RequestMethod.POST)
    @ResponseBody
    public Object testJson(){return "Json提交成功";}
}
