package com.palmtech.ad.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.org.rapid_framework.page.Page;

import com.alibaba.fastjson.JSON;
import com.common.plugins.myframework.BaseRestSpringController;
import com.common.plugins.spring.mvc.Constants;
import com.common.plugins.spring.mvc.TimeEditor;
import com.palmtech.ad.entity.ad.Apk;
import com.palmtech.ad.entity.ad.ApkQuery;
import com.palmtech.ad.manager.ApkManager;
import com.palmtech.ad.manager.StApkDailyManager;

/**
 * 渠道
 * @author Administrator
 *
 */
@Controller
public class ApkController extends BaseRestSpringController<Apk, String> {
	

	@Autowired
	ApkManager apkManager;
	

	
	
	@InitBinder
	 public void initDataBinder(WebDataBinder binder) {
		
     
	      
	}
	
	
	@RequestMapping("/apk")
	@RequiresRoles("admin")
	public String query(ModelMap  model,ApkQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		
		Page<Apk> page = apkManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		return "/apk/list" ;
	}
	
	@RequestMapping(value="/apk/add",method=RequestMethod.GET)	
	@RequiresRoles("admin")
	public String toadd(Integer type ,ModelMap  model) {
		
		
		return "/apk/add";
		
	}
    @RequestMapping(value="/apk/add",method=RequestMethod.POST)	
    @RequiresRoles("admin")
	public String add(Apk entity,HttpSession session,ModelMap  model) {
		
		try{
			
				
			apkManager.save(entity);
				session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
				return "redirect:/apk";

		}catch(DataIntegrityViolationException ce){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:对象已经存在");
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:"+e.getMessage());
			e.printStackTrace();
		}
		
		model.addAttribute("entity", entity);
		
		return "/apk/add";
		
	}
    
    
	
    @RequestMapping(value="/apk/edit",method=RequestMethod.GET)	
    @RequiresRoles("admin")
	public String toedit(String code ,ModelMap  model,HttpSession session) {
		
    	Apk entity =apkManager.getByPk(code);
		if(null==entity){
			  session.setAttribute(Constants.ERR_MESSAGE,"对象不存在Channel:"+code);
			
		}
		model.addAttribute("entity", entity);
		return "/apk/edit";
		
	}
    
    @RequestMapping(value="/apk/edit",method=RequestMethod.POST)	
    @RequiresRoles("admin")
   	public String edit(Apk entity,HttpSession session,ModelMap  model) {
   		
   		try{
   			Apk old = apkManager.getByPk(entity.getCode());
   			old.setApp(entity.getApp());
   			old.setRemark(entity.getRemark());
   			old.setMarket(entity.getMarket());
   			old.setType(entity.getType());
   			old.setRate(entity.getRate());
   			old.setUnitPrice(entity.getUnitPrice());
   			apkManager.update(old);
   				session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
   				return "redirect:/apk";

   		}catch(DataIntegrityViolationException ce){
   			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:对象已经存在");
   		}catch(Exception e){
   			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:"+e.getMessage());
   			e.printStackTrace();
   		}
   		model.addAttribute("entity", entity);
   		return "/apk/edit";
   		
   	}
    @RequestMapping(value="/apk/ajaxInvalid")	
    @RequiresRoles("admin")
 	public void ajaxInvalid(String code,HttpServletResponse response) throws IOException  {
    	response.setContentType("text/html");
 		try{
 				Apk entity = apkManager.getByPk(code);
 				entity.setStatus(1);
 				apkManager.update(entity);
 				
 				response.getWriter().write("success");

 		}catch(Exception e){
 			
 			String message =getCause(e).getMessage();
			String errmsg =message;
			response.getWriter().write(errmsg);
 		}
 		
 		
 		
 	}
    
    @RequestMapping(value="/apk/ajaxValid")	
    @RequiresRoles("admin")
 	public void ajaxValid(String code,HttpServletResponse response) throws IOException  {
    	response.setContentType("text/html");
 		try{
 				Apk entity = apkManager.getByPk(code);
 				
 				entity.setStatus(0);
 				apkManager.update(entity);
 				
 				response.getWriter().write("success");

 		}catch(Exception e){
 			
 			String message =getCause(e).getMessage();
			String errmsg =message;
			response.getWriter().write(errmsg);
 		}
 		
 		
 		
 	}
	
    @Autowired
    StApkDailyManager stApkDailyManager;
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/apk/ajaxGetapkInfo")
	public void getApkInfo(String apkCode,String calendar, HttpServletResponse response) throws IOException{
		
		String json = Constants.ERROR;
		
		try{
			Date date = DateUtils.parseDate(calendar, "yyyy-MM-dd");
			Apk apk = apkManager.getByPk(apkCode);
			if(apk==null){
				throw new  RuntimeException("找不到渠道："+apkCode);
			}
			if(!apk.getType().equalsIgnoreCase("cpa")){
				throw new  RuntimeException("这个APK不是CPA类型："+apkCode);
			}
			long increaments =stApkDailyManager.findIncreaments(apk.getCode(), date);
			
			 Map statis =  new HashMap();
			 statis.put("calendar", calendar);
			 statis.put("increaments", increaments);
			
			Map map = new HashMap();
			map.put("apk", apk);
			map.put("statis", statis);
			
			json = JSON.toJSONString(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.getWriter().write(json);
	}
	


}
