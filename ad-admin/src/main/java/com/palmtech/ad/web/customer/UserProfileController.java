package com.palmtech.ad.web.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.common.plugins.myframework.BaseRestSpringController;
import com.common.plugins.spring.mvc.Constants;
import com.palmtech.ad.entity.User;
import com.palmtech.ad.manager.UserManager;
import com.palmtech.ad.utils.CommonUtil;
/**
 * 后台框架页面
 * @author Administrator
 *
 */
@Controller



public class UserProfileController extends BaseRestSpringController<User, Integer>{

	@Autowired
	UserManager userManager;
	
	

	
	
	
	
	/**
	 * 修改密码
	 * @param id
	 * @param user
	 * @param password1
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/userProfile/editpw")
	public ModelAndView editpw(String password,String password1, HttpServletRequest request,HttpSession session) {
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		User user =userManager.findByUsername(username);
		
		
		if("get".equalsIgnoreCase(request.getMethod())){
			if(null==user){
				session.setAttribute(Constants.ERR_MESSAGE,"对象不存在User:"+username);
				
			}
			return  new ModelAndView("/userProfile/editpw","user",user);
		}
		try{
			boolean validate =true;
			
			if(!StringUtils.hasText(password)||password.length()<6 ||password.length()>30){
				session.setAttribute(Constants.ERR_MESSAGE, "密码不能少于6个字符,大于30个字符");
				validate =false;
			}
			if(!password1.equals(password)){
				session.setAttribute(Constants.ERR_MESSAGE, "密码不一致");
				validate =false;
				
			}
			if(validate){
				User old =userManager.findById(user.getId());
				old.setPassword(CommonUtil.hash(password));
				userManager.update(old);
				session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
				return new ModelAndView("redirect:/userProfile/editpw");
			}
			
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息："+e.getMessage());
			e.printStackTrace();
			
		}
		return new ModelAndView("/userProfile/editpw","user",user);
	}
	
	
	
	

	
}
