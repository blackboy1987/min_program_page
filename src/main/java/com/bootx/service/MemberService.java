
package com.bootx.service;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.App;
import com.bootx.entity.Member;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface MemberService extends BaseService<Member,Long> {

    Page<Member> findPage(Pageable pageable, App app);
}