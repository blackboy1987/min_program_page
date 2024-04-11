package com.bootx.repository;

import com.bootx.entity.Member;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author black
 */
@Repository
public interface MemberRepository extends PagingAndSortingRepository<Member,Long>, JpaSpecificationExecutor<Member> {
}
