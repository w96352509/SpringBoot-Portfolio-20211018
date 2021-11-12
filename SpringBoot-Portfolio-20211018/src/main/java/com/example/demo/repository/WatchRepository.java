package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Watch;

@Repository(value = "watchRepository")
public interface WatchRepository extends JpaRepository<Watch, Integer>{

}