package com.dxc.createclient.validator;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dxc.createclient.dao.UserDAO;
import com.dxc.createclient.entity.User;
import com.dxc.createclient.model.UserInfo;

@Component
public class UserInfoValidator implements Validator{
	
	@Autowired
	private UserDAO userDAO;
	
	public boolean supports(Class<?> clazz) {
		return clazz == UserInfo.class;
	}

	public void validate(Object target, Errors errors) {
		UserInfo userInfo = (UserInfo) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.userForm.firstName");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "NotEmpty.userForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "identity", "NotEmpty.userForm.identity");
		ValidationUtils.rejectIfEmpty(errors, "address", "NotEmpty.userForm.address");
		
		String identity = userInfo.getIdentity();
		User userNew = userDAO.findUser(identity);
		if (userNew != null) {
			if (userNew.getClientNumber().equals(userInfo.getClientNumber())==false) {
				errors.rejectValue("identity", "Duplicate.userForm.identity");
			}
		}
		
		String sdate = userInfo.getBirth();
		DateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = fm.parse(sdate);
			if (date.compareTo(new Date())>0) {
				errors.rejectValue("birth", "NotEmpty.userForm.birth");
			}
		} catch (Exception e) {
			errors.rejectValue("birth", "NotEmpty.userForm.birth");
		}
		String gender = userInfo.getGender();
		if (gender.equalsIgnoreCase("NONE")) {
			errors.rejectValue("gender", "NotEmpty.userForm.gender");
		}
		String marital = userInfo.getMarital();
		if (marital.equalsIgnoreCase("NONE")) {
			errors.rejectValue("marital", "NotEmpty.userForm.marital");
		}
		String country = userInfo.getCountry();
		if (country.equalsIgnoreCase("NONE")) {
			errors.rejectValue("country", "NotEmpty.userForm.country");
		}
	}
	
}
