package com.Trillion.RestEnd.repository;

import com.Trillion.RestEnd.bo.CIDR;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA based repository used for CRUD operations
 */
public interface CidrRepository extends JpaRepository<CIDR, Integer> { }
