package com.bootx.repository;

import com.bootx.entity.App;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author black
 */
@Repository
public interface AppRepository extends PagingAndSortingRepository<App,Long>, JpaSpecificationExecutor<App> {
}
