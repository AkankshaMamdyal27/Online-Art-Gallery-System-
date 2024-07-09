package com.art.galley.service;

import java.util.List;

import java.util.Optional;
import com.art.galley.entity.Product;

public interface ArtAdmService
{
	public Art storeFile(Art art);
	public Optional<Art> getArtId(Long aid);
	public List<Art> getAllArt();
	public void deleteArt(Long aid);
	public void deleteAll(List<Art> ids);
	public Long countArt();
}
