package com.bootx.service;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.App;

/**
 * @author black
 */
public interface AppService extends BaseService<App,Long>{

    Page<App> findPage(Pageable pageable, String appName);

}
