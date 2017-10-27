package com.noisyle.demo.druid;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DemoRepository extends PagingAndSortingRepository<DemoEntity, Long> {
    List<DemoEntity> findAll();
}
