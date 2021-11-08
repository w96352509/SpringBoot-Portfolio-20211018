package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Classify;

@Repository
public interface ClassifyRepository extends JpaRepository<Classify , Integer>{
  
	@Transactional
	@Modifying
	@Query(value = "UPDATE Classify SET name=?2,tx=?3 WHERE id=?1", nativeQuery = true)
	public void update(@Param("id") Integer id ,@Param("name") String name,@Param("tx") Boolean tx);
	
}
