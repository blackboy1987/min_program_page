package com.bootx.controller.admin;


import com.bootx.common.Pageable;
import com.bootx.common.Result;
import com.bootx.entity.Admin;
import com.bootx.entity.App;
import com.bootx.security.CurrentUser;
import com.bootx.service.AppService;
import com.bootx.util.DateUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author black
 */
@RestController
@RequestMapping("/admin/api/app")
public class AppController{

    @Resource
    private AppService appService;

    @PostMapping("/list")
    public Result list(Pageable pageable,String appName){
        return Result.success(appService.findPage(pageable,appName));
    }

    @PostMapping("/create")
    public Result create(@CurrentUser Admin admin){
        App app = new App();
        app.setAppId("测试appId"+System.currentTimeMillis());
        app.setAppName("测试appName"+System.currentTimeMillis());
        app.setAppSecret("测试appSecret"+System.currentTimeMillis());
        app.setAppTypeId(0L);
        appService.save(app);
        return Result.success();
    }

    @PostMapping("/delete")
    public Result delete(@CurrentUser Admin admin,Long[] ids){
        appService.delete(ids);
        return Result.success();
    }

    @PostMapping("/detail")
    public Result detail(@CurrentUser Admin admin,Long id){
        Map<String,Object> data = new HashMap<>();
        App app = appService.find(id);
        if(app!=null){
            data.put("id",app.getId());
            data.put("appId",app.getAppId());
            data.put("appSecret",app.getAppSecret());
            data.put("appName",app.getAppName());
            data.put("appLogo",app.getAppLogo());
        }
        return Result.success(data);
    }
}
