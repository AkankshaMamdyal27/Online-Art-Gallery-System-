package com.art.galley.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.galley.entity.Customer;

public interface UserAdmRepository extends JpaRepository<Customer,Long>
{

}
