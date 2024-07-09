package com.art.gallery.controller;

import java.util.Arrays;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.art.gallery.entity.Art;
import com.art.gallery.entity.Customer;
import com.art.gallery.entity.Order;
import com.art.gallery.entity.OrderDetail;
import com.art.gallery.entity.Artist;
import com.art.gallery.service.ArtService;
import com.art.gallery.service.CustomerService;
import com.art.gallery.service.OrderDetailService;
import com.art.gallery.service.OrderService;
import com.art.gallery.service.ArtistService;

@Controller
@RequestMapping("/art")
public class ArtController {
	private static final Logger log = LoggerFactory.getLogger(ArtController.class);
	
	@Autowired
	private ArtService artService;
	
	@Value("${count}")
	private int order_count;
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	ArtService artService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetailService orderDetailService;

	@PostMapping("/saveArt")
	String orderCheckout(Customer customer, Art art, HttpServletRequest request, HttpSession session,
			RedirectAttributes rda) {
		try {
			session = request.getSession(false);
			String email = (String) session.getAttribute("email");
			if (email == null) {
				String backUrl = request.getHeader("referer");
				session.setAttribute("backUrl", backUrl);
				return "redirect:/customer/login";
			}
			Customer emailExists = customerService.findCustomerByEmail(email);
			Long customerId = emailExists.getId();
			int count = Integer.parseInt(request.getParameter("art_count"));
			for (int i = 1; i <= count; i++) {
				log.info("art_id = " + Long.parseLong(request.getParameter("art_id_" + i)));
				log.info("art_name = " + request.getParameter("art_name_" + i));
				log.info("quantity = " + Integer.parseInt(request.getParameter("quantity_" + i)));
				log.info("amount = " + Double.parseDouble(request.getParameter("amount_" + i)));
				Long artId = Long.parseLong(request.getParameter("art_id_" + i));
				int quantity = Integer.parseInt(request.getParameter("quantity_" + i));
				double mrpPrice = Double.parseDouble(request.getParameter("mrp_" + i));
				double price = Double.parseDouble(request.getParameter("amount_" + i));
				double totalPrice = quantity * price;
				log.info("total_price = " + totalPrice);
				art.setId(artId);
				customer.setId(customerId);
				Artist ArtistArts= new Artist(quantity, mrpPrice, price, customer, art, totalPrice);
				List<Artist> artistArtsList = Arrays.asList(ArtistArts);
				artistService.saveArtistArts(artistArtsList);
				log.info("Artist Arts Inserted :"+i);
			}
			rda.addFlashAttribute("artist", "artist");
			return "redirect:/home";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/home";
		}
	}

	@GetMapping("/my-arts")
	String showMyCartItems(Customer customer, HttpServletRequest request, HttpSession session, Model model) {
		try {
			session = request.getSession(false);
			String email = (String) session.getAttribute("email");
			if (email != null) {
				Customer emailExists = customerService.findCustomerByEmail(email);
				Long customerId = emailExists.getId();
				customer.setId(customerId);
				List<Arts> arteList = artService.getArtsByCustomerId(customer);
				double total_mrp = 0;
				int total_qty = 0;
				double total_price = 0;
				double total_saving = 0;
				for(Art a: ArtistArtsList) {
					total_qty += c.getQuantity();
					total_mrp += c.getMrpPrice() * c.getQuantity();
					total_price += c.getPrice() * c.getQuantity();
				}
				total_saving = total_mrp - total_price;
				model.addAttribute("total_saving", total_saving);
				model.addAttribute("total_mrp", total_mrp);
				model.addAttribute("total_qty", total_qty);
				model.addAttribute("total_price", total_price);
				model.addAttribute("arts", artistArtsList);
				return "cart-list";
			}
			return "redirect:/home";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/home";
		}
	}

	@PostMapping("/checkout")
	String productCheckout(@RequestParam("code") String code, HttpServletRequest request, HttpSession session, 
			Art art, Model model, Artist artist, Customer customer) {
		try {
			session = request.getSession(false);
			String email = (String) session.getAttribute("email");
			if(email == null) {
				String backUrl = request.getHeader("referer");
				log.info("backUrl :: "+backUrl);
				session.setAttribute("backUrl", backUrl);
				return "redirect:/customer/login";
			}
			log.info("Code :: "+code);
			if (code != null && code.startsWith("A")) {
				art = artService.getArtByCode(code);
				log.info("art :: " + art.getName());
				if (art != null) {
					art.setId(art.getId());
					customer = customerService.findCustomerByEmail(email);
					Long customerId = customer.getId();
					customer.setId(customerId);
					art = new Art(1, art.getMrpPrice(), art.getPrice(), customer, art, art.getPrice());
					ArtService.saveArt(art);
					log.info("Artist : Art Inserted.");
					return "redirect:/art/my-art";
				}
			}
			return "redirect:/page404";
		}  catch (Exception e) {
			e.printStackTrace();
			return "redirect:/home";
		}
	}
	
	@GetMapping(value = "/remove/{cid}")
	String removeCartItems(@PathVariable("cid") Long cartId, HttpServletRequest request, HttpSession session,
			RedirectAttributes rda, Customer customer, Product product) {
		String email = "";
		try {
			session = request.getSession(false);
			email = (String) session.getAttribute("email");
			if (email != null) {
				Long customerId = customerService.getCustomerId(email);
				customer.setId(customerId);
				cartService.removeCartItems(customer, cartId);
				rda.addFlashAttribute("delete", "delete");
				return "redirect:/cart/my-cart";
			}
			return "redirect:/home";
		} catch (Exception e) {
			e.printStackTrace();
			rda.addFlashAttribute("error", "error");
			return "redirect:/cart/my-cart";
		}
	}

	@PostMapping("/order/checkout")
	String cartItemsCheckout(@RequestParam("customerName") String customerName,
			@RequestParam("customerPhone") String customerPhone, @RequestParam("address") String customerAddress,
			@RequestParam("addressType") String customerAddressType, @RequestParam("pinCode") String pinCode, HttpServletRequest request, HttpSession session,
			Order order, OrderDetail od, Art art, Customer customer, RedirectAttributes rda) {
		int x, y, count, orderNum, paymentId;
		x = y = orderNum = paymentId = count = 0;
		String orderStatus = "Pending";
		double total = 0;
		double total_mrp=0;
		Date orderDate = new Date();
		try {
			session = request.getSession(false);
			String customerEmail = (String) session.getAttribute("email");
			if (customerEmail != null) {
				Pageable pageable = PageRequest.of(0, 1);
				List<OrderDetail> orderList = orderDetailService.getLastOrderByIdDesc(pageable);
				if(orderList.size() == 0) {
					paymentId = order_count;
					log.info("In List Size 0, Payment Id :: "+paymentId);
				} else {
					paymentId = orderList.get(0).getPaymentId() + 1;
					log.info("In else, Payment Id :: "+paymentId);
				}
				count = (Integer) session.getAttribute("size");
				String paymentMode = request.getParameter("paymentMode");
				int total_qty = 0;
				for (int i = 1; i <= count; i++) {
					log.info("Cart Id :: " + request.getParameter("cid_" + i));
					log.info("Art Id :: " + request.getParameter("aid_" + i));
					log.info("Art Name :: " + request.getParameter("aname_" + i));
					log.info("Art Price :: " + request.getParameter("aprice_" + i));
					Long ordersCount = orderService.getOrdersCount();
					log.info("Total Order Count :: "+ordersCount);
					if(ordersCount == 0) {
						orderNum = order_count;
						log.info("In If :: orderNum :: "+orderNum);
					}
					orderNum = ordersCount.intValue() + order_count;
					log.info("Not In If :: orderNum :: "+orderNum);
					double totalAmount = Double.parseDouble(request.getParameter("total_amount_" + i));
					boolean active = true;
					Long artId = Long.parseLong(request.getParameter("cid_" + i));
					Long cartId = Long.parseLong(request.getParameter("cid_" + i));
					double price = Double.parseDouble(request.getParameter("price_" + i));
					double totalPrice = quantity * amount;
					total += quantity * amount;
					total_mrp += mrpPrice * quantity;
					art.setId(artId);
					log.info("orderNum :: "+orderNum);
					order = new Order(orderNum, totalAmount, customerName, customerAddress, customerAddressType, customerEmail, customerPhone, pinCode, active, orderDate);
					List<Order> orders = Arrays.asList(order);
					orderService.saveOrders(orders);
					log.info("======================================");
					Long id = orderService.getOrderIdByNum(orderNum);
					order.setId(id);
					x++;
					log.info("order id =" + id +" x = "+x);
					boolean flag = orderDetailService.saveCartOrderDetail(order, product, quantity, mrpPrice, amount, totalPrice, paymentId, orderStatus, paymentMode);
					log.info("Flag = "+flag);
					if (flag) {
						y++;
						log.info("order detail saved. y = " + y);
					}
					Long customerId = customerService.getCustomerId(customerEmail);
					customer.setId(customerId);
					artService.removeArtistArts(customer, artId);
				}
				double totalSavings = total_mrp - total;
				rda.addFlashAttribute("orderDate", orderDate);
				rda.addFlashAttribute("totalAmount", total);
				rda.addFlashAttribute("totalMrp", total_mrp);
				rda.addFlashAttribute("paymentId", paymentId);
				rda.addFlashAttribute("pinCode", pinCode);
				rda.addFlashAttribute("totalQty", total_qty);
				rda.addFlashAttribute("totalSavings", totalSavings);
				rda.addFlashAttribute("success", "success");
				return "redirect:/order/payment";
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (x != count) {
				log.info("In catch :: "+orderNum);
				orderService.deleteOrdersByNum(orderNum);
				log.info("deleting from orders table. " + x);
				rda.addFlashAttribute("error", "error");
			}
			if (y != count) {
				log.info("In catch :: "+paymentId);
			orderDetailService.deleteOrderDetailByNum(paymentId);
			log.info("deleting from order_detail table. " + y);
			rda.addFlashAttribute("error", "error");
			return "redirect:/order/payment";
		}
	}
		return "redirect:/home";
 }
}