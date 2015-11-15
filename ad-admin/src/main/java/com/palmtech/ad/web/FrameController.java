package com.palmtech.ad.web;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.palmtech.ad.entity.Role;
import com.palmtech.ad.manager.RoleManager;
import com.palmtech.ad.manager.UserManager;
/**
 * 后台框架页面
 * @author Administrator
 *
 */
@Controller
public class FrameController {
	@Autowired
	UserManager userManager;

	
	@Autowired
	RoleManager roleManager;
	
	@RequestMapping("/admin")
	public String frame() {
		
		return "frame";
	}

	@RequestMapping("/top")
	public String top() {
		
		return "top";
	}
	@RequestMapping("/left")
	public ModelAndView menu() {
		Subject c =	SecurityUtils.getSubject();
		
		//检查是否配置角色
		Boolean hasAnyRoleFlag  = false; 
		List<Role> roles =roleManager.findAll();
		
		for(Role role :roles){
			if(c.hasRole(role.getName())){
				hasAnyRoleFlag = true;
				break;
			}
		}
		if(hasAnyRoleFlag){
			
			if(c.hasRole("admin")){
				return new ModelAndView("left");
			}
			
			if(c.hasRole("cps")){
				return new ModelAndView("menu/cps_left");
			}
			if(c.hasRole("cpa")){
				return new ModelAndView("menu/cpa_left");
			}
			
			return new ModelAndView("left");
			
		}else{
			return new ModelAndView("menu/nothing_left");
		}
		
		
	}
	@RequestMapping("/welcome")
	public String welcome() {
		
		return "welcome";
	}
	
}
