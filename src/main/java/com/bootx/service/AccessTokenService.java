
package com.bootx.service;

import com.bootx.entity.AccessToken;
import com.bootx.entity.App;

/**
 * @author black
 */
public interface AccessTokenService extends BaseService<AccessToken, Long> {

    AccessToken getToken(App app);

    String get(App app);
}