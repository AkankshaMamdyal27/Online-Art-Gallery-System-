package com.art.galley.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.art.galley.entity.Artist;
import com.art.galley.entity.Customer;
import com.art.galley.repository.ArtistRepository;
import com.art.galley.service.ArtistService;

@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {
	
	@Autowired
	private ArtistRepository artRepository;

	@Override
	public List<Artist> getCartItemsByCustomerId(Customer customer) {
		return ArtistRepository.findArtistItemsByCustomer(customer);
	}

	@Override
	public void removeArtistArts(Customer customer, Long id) {
		artistRepository.deleteArtistArts(customer, id);
	}

	@Override
	public void removeArtistArts(Long id) {
		artistRepository.deleteArtistArts(id);
	}

	@Override
	public void saveArtistArts(List<Cart> artistArts {
		artistRepository.saveAll(ArtistArts);
	}

	@Override
	public void saveArts(Art art) {
		artistRepository.save(art);
	}

}
