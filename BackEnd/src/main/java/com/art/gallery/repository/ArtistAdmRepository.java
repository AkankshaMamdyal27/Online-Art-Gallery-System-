package com.art.galley.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.galley.entity.Product;

public interface ArtistAdmRepository extends JpaRepository<Product, Long> {
}
