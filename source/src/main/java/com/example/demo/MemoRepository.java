package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long>{
	Long countByUseridAndPassword(String userid, String password);
	@Transactional
	Long deleteByUserid(String userid);
	Optional<Memo> findByUserid(String userId);
}
