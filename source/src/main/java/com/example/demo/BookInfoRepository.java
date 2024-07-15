package com.example.demo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;


@Repository
public interface BookInfoRepository extends JpaRepository<BookInfo, Long>{
	@Transactional
	void deleteById(Long id);
	List<BookInfo> findByNameContaining(String search);
}
