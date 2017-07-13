package com.noisyle.demo.lucene.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.noisyle.demo.lucene.model.Cnaps;

public interface CnapsRepository extends PagingAndSortingRepository<Cnaps, Long> {
	public List<Cnaps> findByBankNameIgnoreCaseContaining(String bankName);
}
