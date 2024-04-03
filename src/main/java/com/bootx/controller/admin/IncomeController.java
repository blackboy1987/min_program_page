package com.bootx.controller.admin;


import com.bootx.common.Pageable;
import com.bootx.common.Result;
import com.bootx.entity.App;
import com.bootx.entity.BaseEntity;
import com.bootx.service.AccessTokenService;
import com.bootx.service.AppService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author black
 */
@RestController
@RequestMapping("/admin/api/income")
public class IncomeController {

    @Resource
    private AccessTokenService accessTokenService;

    @Resource
    private AppService appService;

    @PostMapping("/list")
    @JsonView(BaseEntity.PageView.class)
    public Result list(Pageable pageable){
        return Result.success(accessTokenService.findPage(pageable));
    }
    @PostMapping("/pull")
    public Result list(Long appId){
        App app = appService.find(appId);
        accessTokenService.getToken(app);
        return Result.success();
    }
}
