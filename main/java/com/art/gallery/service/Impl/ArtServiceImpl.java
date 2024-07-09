package com.art.galley.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.art.galley.entity.Art;
import com.art.galley.repository.ArtRepository;
import com.art.galley.service.ArtService;

@Service
@Transactional
public class ArtServiceImpl implements ArtService {

	@Autowired
	private ArtRepository artRepository;

	@Override
	public List<Arts> getAllActiveArts() {
		return artsRepository.findAllActivearts();
	}

	@Override
	public List<Art> getArts(Pageable pageable) {	
		return artRepository.findArts(pageable);
	}

	@Override
	public Art getArtByCode(String code) {
		return artRepository.findArtByCode(code);
	}

	@Override
	public boolean saveArt(Art art) {
		boolean flag = false;
		if (art != null) {
			artRepository.save(art);
			flag = true;
			return flag;
		}
		return flag;
	}

	@Override
	public List<Art> searchArts(String name) {
		return artRepository.findSearchedArts(name);
	}

	@Override
	public void updateArtByCode(String name, String description, String imageData, double mrpPrice, double price, boolean active, String code) {
		artRepository.updateArtDetails(name, description, imageData, price);
	}

}
