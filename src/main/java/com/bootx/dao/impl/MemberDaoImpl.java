
package com.bootx.dao.impl;

import com.bootx.dao.MemberDao;
import com.bootx.entity.Member;
import org.springframework.stereotype.Repository;

/**
 * Dao - 插件配置
 * 
 * @author blackboy
 * @version 1.0
 */
@Repository
public class MemberDaoImpl extends BaseDaoImpl<Member, Long> implements MemberDao {

}