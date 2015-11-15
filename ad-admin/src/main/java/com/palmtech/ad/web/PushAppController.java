package com.palmtech.ad.web;

import java.io.IOException;
import java.util.List;

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

import com.common.plugins.ConvertTools;
import com.common.plugins.myframework.BaseRestSpringController;
import com.common.plugins.spring.mvc.Constants;
import com.palmtech.ad.entity.Country;
import com.palmtech.ad.entity.ad.PushApp;
import com.palmtech.ad.entity.ad.PushAppQuery;
import com.palmtech.ad.manager.CountryManager;
import com.palmtech.ad.manager.PushAppManager;

/**
 * 黑名单管理
 * @author Administrator
 *
 */
@Controller
public class PushAppController extends BaseRestSpringController<PushApp, Integer> {
	

	@Autowired
	PushAppManager pushAppManager;
	
	@Autowired
	CountryManager countryManager;
	

	
	
	@RequestMapping("/pushApp")
	@RequiresRoles("admin")
	public String query(ModelMap  model,PushAppQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		Page<PushApp> page = pushAppManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/pushApp/list" ;
	}
	
	@RequestMapping(value="/pushApp/add",method=RequestMethod.GET)	
	public String toadd(String type,ModelMap  model) {
		
		List<Country> countrys = countryManager.findAll();
		model.addAttribute("countrys", countrys);
		return "/pushApp/add"+type;
		
	}
    @RequestMapping(value="/pushApp/add",method=RequestMethod.POST)	
	public String add(PushApp entity,HttpSession session,ModelMap  model) {
		
		try{
			
			
			String countryArr = entity.getCountryArr();
			if(StringUtils.hasText(countryArr)){
				entity.setCountrys(ConvertTools.commaString2JsonStr(countryArr));
			}
				pushAppManager.save(entity);
				session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
				return "redirect:/pushApp";

		}catch(DataIntegrityViolationException ce){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:对象已经存在");
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:"+e.getMessage());
			e.printStackTrace();
		}
		List<Country> countrys = countryManager.findAll();
		model.addAttribute("countrys", countrys);
		model.addAttribute("entity", entity);

		return "/pushApp/add"+entity.getType();
		
	}
    
   
	
	
	@RequestMapping("/pushApp/del")
	public void del(String code, HttpServletResponse response)  throws IOException {
		response.setContentType("text/json");
		try {
			pushAppManager.deleteByPk(code);			
			response.getWriter().write("success");
			return;
		} catch (Exception e) {
			String message =getCause(e).getMessage();
			response.getWriter().write(message);
		}
		
		
	}
	
	  @RequestMapping(value="/pushApp/edit",method=RequestMethod.GET)	
		public String toedit(String code ,ModelMap  model,HttpSession session) {
			
	    	PushApp entity =pushAppManager.getByPk(code);
			if(null==entity){
				  session.setAttribute(Constants.ERR_MESSAGE,"对象不存在code:"+code);
				
			}
			model.addAttribute("entity", entity);
	    	
			model.addAttribute("type", entity.getType());
		
			 
			List<Country> countrys = countryManager.findAll();
			model.addAttribute("countrys", countrys);
			return "/pushApp/edit"+entity.getType();
			
		}
	    
	    @RequestMapping(value="/pushApp/edit",method=RequestMethod.POST)	
	   	public String edit(PushApp entity,HttpSession session,ModelMap  model) {
	   		
	   		try{
	   			PushApp old = pushAppManager.getByPk(entity.getCode());
	   				
	   				String countryArr = entity.getCountryArr();
	   				if(StringUtils.hasText(countryArr)){
	   					old.setCountrys(ConvertTools.commaString2JsonStr(countryArr));
	   				}
	   				
	   			
	   			
	   				pushAppManager.update(old);
	   				session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
	   				return "redirect:/pushApp?type="+entity.getType();

	   		}catch(DataIntegrityViolationException ce){
	   			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:对象已经存在");
	   		}catch(Exception e){
	   			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:"+e.getMessage());
	   			e.printStackTrace();
	   		}
	   		
	   		model.addAttribute("entity", entity);
	   		model.addAttribute("type", entity.getType());
	   	 
			List<Country> countrys = countryManager.findAll();
			model.addAttribute("countrys", countrys);

	   		return "/pushApp/edit"+entity.getType();
	   		
	   	}

}
