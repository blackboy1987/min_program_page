package com.bootx.service.impl;

import com.bootx.entity.AccessToken;
import com.bootx.entity.App;
import com.bootx.service.AccessTokenService;
import com.bootx.util.DateUtils;
import com.bootx.util.wechat.WechatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author black
 */
@Service
public class AccessTokenServiceImpl extends BaseServiceImpl<AccessToken,Long> implements AccessTokenService {

    @Override
    public AccessToken getToken(App app) {
        com.bootx.util.wechat.AccessToken accessToken = WechatUtils.getAccessToken(app);
        AccessToken accessToken1 = new AccessToken();
        accessToken1.setToken(accessToken.getAccessToken());
        accessToken1.setExpiresIn(accessToken.getExpiresIn());
        accessToken1.setApp(app);
        accessToken1.setExpireDate(DateUtils.getNextSecond(accessToken1.getExpiresIn()));
        super.save(accessToken1);
        redisService.set("accessToken:"+app.getId(),accessToken1.getToken(),accessToken1.getExpiresIn(), TimeUnit.SECONDS);
        return accessToken1;
    }

    @Override
    public String get(App app) {
        String s = redisService.get("accessToken:" + app.getId());
        if(StringUtils.isNotBlank(s)){
            return s;
        }
        try {
            s = jdbcTemplate.queryForObject("select token from accesstoken where app_id=? and expireDate>NOW() order by expireDate desc limit 1;",String.class,app.getId());
        }catch (Exception ignored){
            AccessToken token = getToken(app);
            s = token.getToken();
        }
        return s;
    }
}
