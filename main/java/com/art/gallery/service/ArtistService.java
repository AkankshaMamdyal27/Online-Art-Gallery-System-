package com.art.galley.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import com.art.galley.entity.Artist;

public interface ArtistService {
	boolean saveArtist(Artist artist);
	List<Artist> getAllActiveArtist();
	List<Artist> getArtists(Pageable pageable);
	public Artist getArtistByCode(String code);
	List<Artist> searchArtists(String name);
	void updateArtistByCode(String name, String description, String imageData, double mrpPrice, double price, boolean active, String code);
}
