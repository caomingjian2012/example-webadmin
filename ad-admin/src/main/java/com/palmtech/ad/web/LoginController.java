package com.palmtech.ad.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam(value = "username",required = true) String username,
			@RequestParam(value = "password",required = true) String password,
			HttpServletRequest request) {
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		token.setHost(request.getRemoteHost());
		token.setRememberMe(true);
		
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
		
			if (subject.isAuthenticated()) {//验证是否成功登录的方法	
				
				return new ModelAndView("redirect:/admin");
			}
		} catch (UnknownAccountException ex) {//用户名没有找到。
			ex.printStackTrace();
		} catch (IncorrectCredentialsException ex) {//用户名密码不匹配。
			ex.printStackTrace();
		}catch (AuthenticationException e) {//其他的登录错误
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/login.jsp");
	}

}
