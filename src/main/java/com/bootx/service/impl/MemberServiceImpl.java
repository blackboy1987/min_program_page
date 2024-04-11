
package com.bootx.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.App;
import com.bootx.entity.Member;
import com.bootx.repository.MemberRepository;
import com.bootx.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Service;

/**
 * Service - 素材目录
 *
 * @author blackboy
 * @version 1.0
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<Member, Long> implements MemberService {

    @Resource
    private MemberRepository memberRepository;

    @Override
    public Page<Member> findPage(Pageable pageable, App app) {
        org.springframework.data.domain.Page<Member> all = memberRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Predicate restrictions = criteriaBuilder.conjunction();
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("appId"), app.getId()));
            criteriaQuery.where(restrictions);
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdDate")));
            return restrictions;
        }, org.springframework.data.domain.Pageable.ofSize(pageable.getPageSize()).withPage(pageable.getPageNumber() - 1));
        return new Page<Member>(all.getContent(),all.getTotalElements(),pageable);
    }
}