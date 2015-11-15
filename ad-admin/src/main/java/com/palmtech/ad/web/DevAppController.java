package com.palmtech.ad.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.org.rapid_framework.page.Page;

import com.common.plugins.myframework.BaseRestSpringController;
import com.common.plugins.spring.mvc.Constants;
import com.palmtech.ad.entity.ad.DevApp;
import com.palmtech.ad.entity.ad.DevAppQuery;
import com.palmtech.ad.manager.DevAppManager;

/**
 * 黑名单管理
 * @author Administrator
 *
 */
@Controller
public class DevAppController extends BaseRestSpringController<DevApp, Integer> {
	

	@Autowired
	DevAppManager devAppManager;
	

	
	
	@RequestMapping("/devApp")
	@RequiresRoles("admin")
	public String query(ModelMap  model,DevAppQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		Page<DevApp> page = devAppManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/devApp/list" ;
	}
	
	@RequestMapping(value="/devApp/add",method=RequestMethod.GET)	
	public String toadd(ModelMap  model) {
		
		return "/devApp/add";
		
	}
    @RequestMapping(value="/devApp/add",method=RequestMethod.POST)	
	public String add(DevApp entity,HttpSession session,ModelMap  model) {
		
		try{
				devAppManager.save(entity);
				session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
				return "redirect:/devApp";

		}catch(DataIntegrityViolationException ce){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:对象已经存在");
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:"+e.getMessage());
			e.printStackTrace();
		}
		
		model.addAttribute("entity", entity);

		return "/devApp/add";
		
	}
    
    @RequestMapping(value="/devApp/ajaxadd",method=RequestMethod.POST)	
 	public void ajaxadd(DevApp entity,HttpServletResponse response) throws IOException  {
    	response.setContentType("text/html");
 		try{
 			
 				devAppManager.save(entity);
 				
 				response.getWriter().write("success");

 		}catch(Exception e){
 			
 			String message =getCause(e).getMessage();
			String errmsg =message;
			response.getWriter().write(errmsg);
 		}
 		
 		
 		
 	}
	
	
	@RequestMapping("/devApp/del")
	public void del(Integer id, HttpServletResponse response)  throws IOException {
//		response.setContentType("text/json");
//		try {
//			devAppManager.deleteByPk(id);			
//			response.getWriter().write("success");
//			return;
//		} catch (Exception e) {
//			String message =getCause(e).getMessage();
//			response.getWriter().write(message);
//		}
		
		
	}
	


}
