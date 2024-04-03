package com.bootx.util;

import org.apache.commons.lang3.StringUtils;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

public class EhCacheUtils {

    private static final CacheManager COMMON_CACHE_MANAGER = CacheManagerBuilder.newCacheManagerBuilder()
            .withCache("common",
                    CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)))
            .build();

    public static Integer getUploadRateCache(String key){
        if(COMMON_CACHE_MANAGER!=null){
            Cache<String, Integer> common = COMMON_CACHE_MANAGER.getCache("common", String.class, Integer.class);
            return common.get(key);
        }
        return 100000;
    }

    public static void setUploadRateCache(String key,Integer value){
        if(COMMON_CACHE_MANAGER!=null&& StringUtils.isNotBlank(key)&&value!=null){
            Cache<String, Integer> common = COMMON_CACHE_MANAGER.getCache("common", String.class, Integer.class);
            common.put(key,value);
        }
    }

    public static void removeUploadRateCache(String key){
        if(COMMON_CACHE_MANAGER!=null&&StringUtils.isNotBlank(key)){
            Cache<String, Integer> common = COMMON_CACHE_MANAGER.getCache("common", String.class, Integer.class);
            common.remove(key);
        }
    }

}
