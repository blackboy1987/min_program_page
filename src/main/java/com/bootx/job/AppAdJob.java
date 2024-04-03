package com.bootx.job;

import com.bootx.entity.App;
import com.bootx.service.AccessTokenService;
import com.bootx.service.AppAdService;
import com.bootx.service.AppService;
import com.bootx.util.DateUtils;
import com.bootx.util.JsonUtils;
import com.bootx.util.wechat.DataCubeUtils;
import com.bootx.util.wechat.response.datacube.DataCubePublisherAdposGeneralResponse;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author black
 */
@Component
public class AppAdJob {

    @Resource
    private AppService appService;

    @Resource
    private AccessTokenService accessTokenService;

    @Scheduled(fixedRate = 1000*60*60*24)
    public void run() throws InterruptedException {
        App app = appService.find(10L);
        Date beginDate = DateUtils.formatStringToDate("2021-03-02 11:23:55","yyyy-MM-dd HH:mm:ss");
        String accessToken = accessTokenService.get(app);
        DataCubePublisherAdposGeneralResponse dataCubePublisherAdposGeneralResponse = DataCubeUtils.publisher_adpos_general(1, 20, beginDate, beginDate,accessToken);
        System.out.println(JsonUtils.toJson(dataCubePublisherAdposGeneralResponse));
        Thread.sleep(1000);
    }

}
