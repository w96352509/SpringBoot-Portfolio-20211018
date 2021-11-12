package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Asset;

@Repository(value = "assetRepository")
public interface AssetRepository extends JpaRepository<Asset, Integer>{
    @Query(value = "SELECT a FROM Asset a WHERE a.invid = ?1")
    public List<Asset> findByInvid(@Param("invid") Integer invid);
    
}