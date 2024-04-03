package com.bootx.dao.impl;

import com.bootx.dao.AppDao;
import com.bootx.entity.App;
import org.springframework.stereotype.Repository;

/**
 * @author black
 */
@Repository
public class AppDaoImpl extends BaseDaoImpl<App,Long> implements AppDao {
}
