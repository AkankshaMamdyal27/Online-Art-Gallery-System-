package com.art.galley.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.general.stores.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query(value = "select c from Customer c where c.name=?1")
	Customer checkCustomerName(String name);
	
	@Query(value = "select c from Customer c where c.contact=?1")
	Customer checkCustomerByContact(String contact);
	
	@Query(value = "select c from Customer c where c.address=?1")
	String getCustomerByAddress(String address);
	
	@Query(value = "select c from Customer c where c.customer_id=?1")
	Customer checkCustomerByCustomer_id(String customer_id);
	
	@Query(value = "select c from Customer c where c.email=?1 and c.customer_id=?2")
	boolean findValidCustomer(String email, String password);
	
	@Modifying(clearAutomatically = true)
	@Query("update Customer c set c.password =:password where c.email =:email")
	void changePassword(@Param("email") String email, @Param("password") String password);
	
	@Query(value = "select c.id from Customer c where c.email=?1")
	Long findCustomerId(String customerEmail);
	
	@Modifying(clearAutomatically = true)
	@Query("update Customer c set c.name =:name,c.address =:address,c.gender = :gender,c.phone =:phone,c.pinCode =:pinCode where c.id =:id")
	void updateMyCustomer(@Param("id") Long id,@Param("name") String name, @Param("address") String address,@Param("gender") String gender, 
			@Param("phone") String phone, @Param("pinCode") String pinCode);
	
	
	
}
