package com.wuyk.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于做页面映射跳转的控制层
 * Created by WUYK on 2019-12-04.
 */
@Controller
public class BaseController {

    /**
     * 跳转到秒杀商品页
     * @return
     */
    @RequestMapping("/seckill")
    public String seckillGoods() {
        return "redirect:/seckill/list";
    }
}
