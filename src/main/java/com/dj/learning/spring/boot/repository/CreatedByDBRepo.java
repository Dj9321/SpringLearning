package com.dj.learning.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dj.learning.spring.boot.entity.CheckTable;

// Create an interface (not class) and mark it as @Repository and extend JpaRepository<Classname, PrimaryKey>
@Repository
public interface CreatedByDBRepo extends JpaRepository<CheckTable, Integer> {

}
