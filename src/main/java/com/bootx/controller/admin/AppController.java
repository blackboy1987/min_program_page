package com.bootx.controller.admin;


import com.bootx.common.Pageable;
import com.bootx.common.Result;
import com.bootx.entity.Admin;
import com.bootx.entity.App;
import com.bootx.security.CurrentUser;
import com.bootx.service.AdminService;
import com.bootx.service.AppService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Resource
    private AdminService adminService;

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


    @PostMapping("/base")
    public Result base(Long id,@CurrentUser Admin admin){
        App app = appService.find(id);
        /*if(app==null){
            return Result.error("应用不存在");
        }
        if(!adminService.check(admin,app)){
            return Result.error("非法访问");
        }*/

        Map<String,Object> data = new HashMap<>(16);
        data.put("id", app.getId());
        data.put("appName", app.getAppName());
        data.put("appLogo",app.getAppLogo());
        data.put("appId", app.getAppId());
        data.put("appSecret", app.getAppSecret());
        data.put("status", app.getStatus());

        return Result.success(data);
    }

    @PostMapping("/baseUpdate")
    public Result baseUpdate(Long id,String appId,String appLogo,String appName,String appSecret,Integer status){
        if(status==null){
            status = 1;
        }
        App app = appService.find(id);
        app.setAppSecret(appSecret);
        app.setAppLogo(appLogo);
        app.setAppName(appName);
        app.setStatus(status);
        app.setAppId(appId);
        appService.update(app);
        return Result.success("操作成功");
    }

}
