package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Investor;

@Repository(value = "investorRepository")
public interface InvestorRepository extends JpaRepository<Investor, Integer>{
    @Query(value = "Select i From Investor i Where i.username=?1")
    public Investor getInvestor(@Param("username") String username);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE Investor SET username=?2, email=?3, balance=?4 WHERE id=?1", nativeQuery = true)
    public void update(@Param("id") Integer id, @Param("username") String username, @Param("email") String email, @Param("balance") Integer balance);
}
