package com.dxc.createclient.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dxc.createclient.dao.AccountDAO;
import com.dxc.createclient.dao.UserDAO;
import com.dxc.createclient.entity.User;
import com.dxc.createclient.model.UserInfo;



@Controller
@Transactional
@EnableWebMvc
public class MainController {
	
	
	@Autowired
	private UserDAO userDao;
	
	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping({"/user_list"})
	public String userListHandler(Model model) {
		List<User> list = userDao.listUser();
		model.addAttribute("listUser", list);
		return "user_list";
	}
	
	@RequestMapping(value = { "/user_infor" }, method = RequestMethod.GET)
	public String userInfor(Model model, @RequestParam(value = "clientNumber") String clientNumber) {
		User user = userDao.findUserByClientNumber(clientNumber);
		model.addAttribute("userInfor", user);
		return "user_infor";
	}
	
	
	
	
}
