package com.dj.learning.spring.boot.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dj.learning.spring.boot.entity.Customer;

// Create an interface (not class) and mark it as @Repository and extend JpaRepository<Classname, PrimaryKey>
@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {

	Optional<Customer> findByEmail(String email);
}
