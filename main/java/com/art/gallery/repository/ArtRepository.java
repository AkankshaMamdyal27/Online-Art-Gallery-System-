package com.art.galley.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.art.galley.entity.Art;
import com.art.galley.entity.Customer;

public interface ArtRepository extends JpaRepository<Cart, Long> {

	@Query(value = "select c from Cart c where c.customer=?1")
	List<Art> findArtItemsByCustomer(Customer customer);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "delete from Cart c where c.customer=?1 and c.id=?2")
	void deleteArt(Customer customer, Long id);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "delete from Cart c where c.id=?1")
	void deleteArt(Long id);
}
