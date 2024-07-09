package com.art.galley.service.Impl;

import java.util.List;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.art.galley.entity.Art;
import com.art.galley.repository.ArtAdmRepository;
import com.art.galley.service.ArtAdmService;

@Service
@Transactional
public class ArtAdmServiceImpl implements ArtAdmService
{
	@Autowired
	ArtAdmRepository artRepo;

	@Override
	public Art storeFile(Art art) {
		return artRepo.save(art);
	}

	@Override
	public Optional<Art> getArtId(Long aid) {
		return artRepo.findById(aid);
	}

	@Override
	public List<Art> getAllArt() {
		return artRepo.findAll();
	}

	@Override
	public void deleteArt(Long aid) {
		artRepo.deleteById(aid);
	}

	@Override
	public void deleteAll(List<Art> ids) {

	}

	@Override
	public Long countArt() {
		return artRepo.count();
	}

}
