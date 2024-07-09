package com.art.galley.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.art.galley.entity.Product;
import com.art.galley.service.ProductAdmService;
import com.art.galley.service.ProductService;

@Controller
public class LinkController {

	@Autowired
	private ArtService artService;

	@Autowired
	ArtAdmService artAdmSer;

	@Value("${arts}")
	private int size;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = { "/", "/index", "/home", "" })
	public String homePage(Model map) {
		Pageable pageable = PageRequest.of(0, size);
		List<Art> art = artService.getArts(pageable);
		Long count = productAdmSer.countProduct();
		log.info("count:: " + count);
		map.addAttribute("count", count);
		map.addAttribute("art", art);
		map.addAttribute("arts", arts);
		log.info("Showing Home Page.");
		return "index";
	}

	@GetMapping(value = { "/arts/{pageNo}" })
	public String homePages(Model model, @PathVariable("pageNo") int pageNo) {
		Long count = artAdmSer.countArt();
		log.info("art:: " + art +" pageNo:: "+pageNo);
		if (pageNo <= 0 || count < size) {
			return "redirect:/home";
		}
		Pageable pageable = PageRequest.of(pageNo, size);
		List<Art> art = artService.getArts(pageable);
		if (!art.isEmpty()) {
			int nextNum = pageNo + 1;
			int backNum = pageNo - 1;
			String nextUrl = "/arts/" + nextNum;
			String backUrl ="";
			if(pageNo == 0) {
				backUrl = "/arts/" + 0;
			} else {
				backUrl = "/arts/" + backNum;
			}
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("nextUrl", nextUrl);
			model.addAttribute("backUrl", backUrl);
			model.addAttribute("products", product);
			log.info("Showing Arts, Page No. "+pageNo);
		} else {
			log.info("In else ... There is no arts.");
			model.addAttribute("pageNo", 0);
			model.addAttribute("art", art);
		}
		return "index";
	}

	@GetMapping("/contact")
	public String contactPage() {
		return "contact-us";
	}

	@PostMapping("/checkout")
	public String servicesPage() {
		return "checkout";
	}

	@GetMapping("/Add_Art")
	public String addArt() {
		return "new-art";
	}

	@GetMapping("/about")
	public String aboutPage() {
		return "about-us";
	}

	@GetMapping("/page404")
	public String pageNotFound() {
		return "page404";
	}

}
