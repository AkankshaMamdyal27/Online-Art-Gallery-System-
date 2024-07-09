package com.art.galley.service;

import java.util.List;

import com.art.galley.entity.Cart;
import com.art.galley.entity.Customer;

public interface ArtService {

	void saveArtItems(List<Cart> ArtsItems);
	void saveArt(Art art);
	List<Art> getArtItemsByCustomerId(Customer customer);
	void removeArtItems(Customer customer, Long id);
	void removeArtItem(Long id);
}
