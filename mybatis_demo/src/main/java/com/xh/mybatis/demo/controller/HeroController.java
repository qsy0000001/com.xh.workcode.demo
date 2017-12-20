package com.xh.mybatis.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.xh.mybatis.demo.dao.model.Hero;
import com.xh.mybatis.demo.service.IHeroOperationService;
import com.xh.mybatis.demo.util.RedisUtils;
import com.xh.mybatis.demo.vo.request.AddHeroRequest;
import com.xh.mybatis.demo.vo.request.UpdateHeroRequest;
import com.xh.mybatis.demo.vo.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xionghao on 2017/12/12.
 */
@Slf4j
@RequestMapping("/test")
@RestController
public class HeroController {

    @Autowired
    IHeroOperationService heroOperationService;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse handleException(HttpServletRequest req,Exception ex) {
        String msg = "Exception=[" + ex.toString() + "] " + req.getAttribute("traceId");
        log.error("{} {}", req.getRequestURI(), msg);
        BaseResponse response = new BaseResponse();
        response.setRet("1");
        response.setMsg(msg);
        return response;
    }

    @RequestMapping(value = "/addHero", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public BaseResponse addHero(@RequestParam("uid") String uid,
                          @RequestParam("token") String token,
                          @RequestBody String request,
                          HttpServletRequest req){
        System.out.println("uid = "+uid+" ,"+" token = "+token);
        BaseResponse response = new BaseResponse();
        AddHeroRequest heroRequest = JSONObject.parseObject(request, AddHeroRequest.class);
        heroOperationService.addHero(heroRequest);
        response.setRet("0");
        response.setMsg("success");
        return response;
    }

    @RequestMapping(value = "/listHeros", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public BaseResponse listHeros(@RequestParam("uid") String uid,
                                  @RequestParam("token") String token,
                                  HttpServletRequest req){
        System.out.println("uid = "+uid+" ,"+" token = "+token);
        BaseResponse response = new BaseResponse();
        List<Hero> heros = heroOperationService.findHeros();
        response.setRet("0");
        response.setMsg("success");
        response.setResult(heros);
        return response;
    }

    @RequestMapping(value = "/updateHero", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public BaseResponse updateHero(@RequestParam("uid") String uid,
                                   @RequestParam("token") String token,
                                   @RequestBody String request,
                                   HttpServletRequest req){
        System.out.println("uid = "+uid+" ,"+" token = "+token);
        BaseResponse response = new BaseResponse();
        UpdateHeroRequest updateHeroRequest = JSONObject.parseObject(request, UpdateHeroRequest.class);
        heroOperationService.updateHero(updateHeroRequest);
        response.setRet("0");
        response.setMsg("success");
        return response;
    }

    @RequestMapping(value = "/deleteHero", method = RequestMethod.GET, produces = "application/json")
    public BaseResponse deleteHero(@RequestParam("uid") String uid,
                                   @RequestParam("token") String token,
                                   @RequestParam("id") Integer id,
                                   HttpServletRequest req){
        System.out.println("uid = "+uid+" ,"+" token = "+token);
        BaseResponse response = new BaseResponse();
        heroOperationService.deleteHero(id);
        response.setRet("0");
        response.setMsg("success");
        return response;
    }

    @RequestMapping(value = "/findHero", method = RequestMethod.GET, produces = "application/json")
    public BaseResponse findHero(@RequestParam("uid") String uid,
                                   @RequestParam("token") String token,
                                   @RequestParam("id") Integer id,
                                   HttpServletRequest req){
        System.out.println("uid = "+uid+" ,"+" token = "+token);
        BaseResponse response = new BaseResponse();
        Hero hero = heroOperationService.findById(id);
        response.setRet("0");
        response.setMsg("success");
        response.setResult(hero);
        return response;
    }
}
