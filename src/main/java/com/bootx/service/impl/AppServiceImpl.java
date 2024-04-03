package com.bootx.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.App;
import com.bootx.repository.AppRepository;
import com.bootx.service.AppService;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author black
 */
@Service
public class AppServiceImpl extends BaseServiceImpl<App,Long> implements AppService {

    @Resource
    private AppRepository appRepository;

    @Override
    public Page<App> findPage(Pageable pageable, String appName) {
        org.springframework.data.domain.Page<App> all = appRepository.findAll((Specification<App>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate restrictions = criteriaBuilder.conjunction();
            if (StringUtils.isNotBlank(appName)) {
                restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.get("appName"), "%"+appName+"%"));
            }
            criteriaQuery.where(restrictions);
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdDate")));
            return restrictions;
        }, org.springframework.data.domain.Pageable.ofSize(pageable.getPageSize()).withPage(pageable.getPageNumber()-1));

        return new Page<App>(all.getContent(),all.getTotalElements(),pageable);


    }

}
