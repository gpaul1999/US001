package com.dxc.createclient.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dxc.createclient.dao.UserDAO;
import com.dxc.createclient.entity.User;
import com.dxc.createclient.model.UserInfo;
import com.dxc.createclient.validator.UserInfoValidator;

@Controller
@Transactional
@EnableWebMvc
public class AdminController {

	private List<String> lsGender = Arrays.asList("Male", "Female", "Unknown");
	private List<String> lsMarital = Arrays.asList("Single", "Married", "Divorced");
	private List<String> lsCountry = Arrays.asList("Vietnam", "Malaysia", "Singapore", "United States");
	private static final Log log = LogFactory.getLog(AdminController.class);
	
	@Autowired
	private UserDAO userDao;

	@Autowired
	private UserInfoValidator userInfoValidator;

	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@InitBinder
	public void myInitBinder (WebDataBinder binder) {
		Object target = binder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target: " + target);
		
		if (target.getClass()==UserInfo.class) {
			binder.setValidator(userInfoValidator);
		}
	}
	
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping("/add_form")
	public String showForm (Model model) {
		UserInfo userInfo = new UserInfo();
		model.addAttribute("userForm", userInfo);
		model.addAttribute("lsGender", lsGender);
		model.addAttribute("lsMarital", lsMarital);
		model.addAttribute("lsCountry", lsCountry);
		return "add_form";
	}

	@RequestMapping(value = {"/add_form"}, method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String addUser(Model  model, @ModelAttribute("userForm") @Validated UserInfo userInfo, 
			BindingResult result, final RedirectAttributes redirectAttribute) {
		model.addAttribute("lsGender", lsGender);
		model.addAttribute("lsMarital", lsMarital);
		model.addAttribute("lsCountry", lsCountry);
		if(result.hasErrors()) {
			log.error("==========ADD USER FAIL!==========");
			return "add_form";
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = null;
		try {
			date = df.parse(userInfo.getBirth());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		User user = new User(userInfo.getFirstName(), userInfo.getLastName(), 
				userInfo.getGender(), date, userInfo.getIdentity(), 
				userInfo.getMarital(), userInfo.getAddress(), userInfo.getCountry());
		System.out.println(user);
		try {
			userDao.addUser(user);
			log.error("==========ADD USER SUCESSFULLY!==========");
		} catch (Exception e) {
			String message = e.getMessage();
			model.addAttribute("message", message);
			log.error("==========ADD USER FAIL!==========");
			return "add_form";
		}
		return "redirect:/result_add";
	}
	
	@RequestMapping("/result_add")
	private String showResult(Model model) {
		return "result_add";
	}
	
	@RequestMapping("/edit_user")
	public String showEditForm (Model model, @RequestParam(value = "clientNumber") String clientNumber) {
		UserInfo userInfo = new UserInfo();
		model.addAttribute("userForm", userInfo);
		User user = userDao.findUserByClientNumber(clientNumber);
		model.addAttribute("userInfor", user);
		model.addAttribute("lsGender", lsGender);
		model.addAttribute("lsMarital", lsMarital);
		model.addAttribute("lsCountry", lsCountry);
		return "edit_user";
	}
	
	@RequestMapping(value = { "/edit_user" }, method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String editUserInfor(Model  model, @RequestParam(value = "clientNumber") String clientNumber, 
			@ModelAttribute("userForm") @Validated UserInfo userInfo, 
			BindingResult result, final RedirectAttributes redirectAttribute) {
		model.addAttribute("lsGender", lsGender);
		model.addAttribute("lsMarital", lsMarital);
		model.addAttribute("lsCountry", lsCountry);
		if(result.hasErrors()) {
			log.error("==========MODIFY USER FAIL!==========");
			return "edit_user";
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = null;
		try {
			date = df.parse(userInfo.getBirth());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		User user = new User(userInfo.getFirstName(), userInfo.getLastName(), 
				userInfo.getGender(), date, userInfo.getIdentity(), 
				userInfo.getMarital(), userInfo.getAddress(), userInfo.getCountry());
		user.setClientNumber(clientNumber);
		System.out.println(user);
		try {
			userDao.updateUserInfor(user);
			log.error("==========MODIFY USER SUCESSFULLY!==========");
		} catch (Exception e) {
			String message = e.getMessage();
			model.addAttribute("message", message);
			log.error("==========MODIFY USER FAIL!==========");
			return "edit_user";
		}
		return "redirect:/result_modify";
	}
	
	@RequestMapping("/result_modify")
	private String showResultModify(Model model) {
		return "result_modify";
	}
}
