package com.palmtech.ad.web.mk;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.plugins.myframework.BaseRestSpringController;
import com.palmtech.ad.entity.User;
import com.palmtech.ad.entity.ad.Apk;
import com.palmtech.ad.entity.mk.UserApk;
import com.palmtech.ad.entity.mk.UserApkQuery;
import com.palmtech.ad.manager.ApkManager;
import com.palmtech.ad.manager.UserApkManager;
import com.palmtech.ad.manager.UserManager;
/**
 * 用户与包关系管理
 * @author Administrator
 *
 */
@Controller


public class UserApkController extends BaseRestSpringController<UserApk, Integer>{


	@Autowired
	UserApkManager userChannelManager;
	@Autowired
	UserManager userManager;
	
	@Autowired
	ApkManager apkManager;
	

	
	@RequestMapping("/userApk")
	@RequiresRoles("admin")
	public String query(ModelMap model,UserApkQuery query) {
		User user = userManager.findByUsername(query.getUsername());
		model.addAttribute("user",user);
		List<UserApk> list = userChannelManager.findAll(query);
		model.addAttribute("list",list);
		return "/userApk/list";
	}

	
	@RequestMapping("/userApk/ajaxAdd")
	@RequiresRoles("admin")
	public void add(String  apkCode,String username, HttpServletResponse response)  throws IOException {
		response.setContentType("text/json");
		try {
			User user = userManager.findByUsername(username);
			if(null==user)
				throw new RuntimeException("用户不存在 :"+username);
			Apk apk = apkManager.getByPk(apkCode);
			if(null==apk)
				throw new RuntimeException("APK不存在。code:"+apkCode);
		    UserApk entity = new UserApk();
		    
		   entity.setApk(apk.getCode());
		    entity.setUsername(user.getUsername());
			userChannelManager.save(entity);
			response.getWriter().write("success");
			return;
		} catch (Exception e) {
			response.setContentType("text/json");
			response.getWriter().write(e.getMessage());
		}
		
		
	}
	
	/**
	 * 修改资料
	 * @param id
	 * @param user
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/userApk/ajaxEdit")
	@RequiresRoles("admin")
	public void edit(UserApk  entity, HttpServletResponse response)  throws IOException {
		response.setContentType("text/json");
		try {
			userChannelManager.update(entity);
			response.getWriter().write("success");
			return;
		} catch (Exception e) {
			
		}
		
		response.setContentType("text/json");

		response.getWriter().write("fail");
	}
	
	


	@RequestMapping("/userApk/del")
	@RequiresRoles("admin")
	public void del(Integer id, HttpServletResponse response)  throws IOException {
		response.setContentType("text/json");
		try {
			userChannelManager.deleteByPk(id);
			response.getWriter().write("success");
			return;
		} catch (Exception e) {
			String message =getCause(e).getMessage();
			response.getWriter().write(message);
		}
		
		
	}
	


	
	

	
}
