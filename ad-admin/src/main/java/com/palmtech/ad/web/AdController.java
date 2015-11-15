package com.palmtech.ad.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.org.rapid_framework.page.Page;

import com.common.plugins.ConvertTools;
import com.common.plugins.myframework.BaseRestSpringController;
import com.common.plugins.spring.mvc.Constants;
import com.common.plugins.spring.mvc.TimeEditor;
import com.palmtech.ad.entity.ad.Ad;
import com.palmtech.ad.entity.ad.AdQuery;
import com.palmtech.ad.entity.ad.PushApp;
import com.palmtech.ad.entity.ad.PushAppQuery;
import com.palmtech.ad.entity.constants.AdStateType;
import com.palmtech.ad.entity.constants.AdType;
import com.palmtech.ad.manager.AdManager;
import com.palmtech.ad.manager.PushAppManager;

/**
 * 广告
 * @author Administrator
 *
 */
@Controller
public class AdController extends BaseRestSpringController<Ad, Integer> {
	

	@Autowired
	AdManager adManager;
	
	@Autowired
	PushAppManager pushAppManager ;
	
	
	@InitBinder
	 public void initDataBinder(WebDataBinder binder) {
	        TimeEditor timeEditor = new TimeEditor();
	        timeEditor.setFormat("yyyy-MM-dd HH:mm:ss");
	        binder.registerCustomEditor(Date.class, "endTime", timeEditor);
	        binder.registerCustomEditor(Date.class, "showTime", timeEditor);
	      
	    }
	
	
	@RequestMapping("/ad")
	@RequiresRoles("admin")
	public String query(ModelMap  model,AdQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		
		if(null==query.getState()){
			query.setState(AdStateType.UP.getValue());;
		}
		//自动下架
		adManager.autoDown();
		
		
		Page<Ad> page = adManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		Map<String,PushApp> pushAppMap = pushAppManager.findAll2Map();
		model.addAttribute("pushAppMap", pushAppMap);
		
		
		return "/ad/list" ;
	}
	
	@RequestMapping(value="/ad/add",method=RequestMethod.GET)	
	public String toadd(Integer type ,ModelMap  model) {
		
		model.addAttribute("type", type);
		Map typeMap = AdType.getMap();
		model.addAttribute("typeMap", typeMap);
		
		//上架APP
		PushAppQuery pushAppQuery = new PushAppQuery();
		pushAppQuery.setPageSize(Integer.MAX_VALUE);
		pushAppQuery.setState(AdStateType.UP.getValue());
		List<PushApp> availPushApps =  pushAppManager.findPage(pushAppQuery).getResult();
		
		model.addAttribute("availPushApps", availPushApps);
		return "/ad/add"+type;
		
	}
    @RequestMapping(value="/ad/add",method=RequestMethod.POST)	
	public String add(Ad entity,HttpSession session,ModelMap  model) {
		
		try{
			
				String channelArr = entity.getChannelArr();
				if(StringUtils.hasText(channelArr)){
					entity.setChannels(ConvertTools.commaString2JsonStr(channelArr));
				}
				
				adManager.save(entity);
				session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
				return "redirect:/ad?type="+entity.getType();

		}catch(DataIntegrityViolationException ce){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:对象已经存在");
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:"+e.getMessage());
			e.printStackTrace();
		}
		
		model.addAttribute("entity", entity);
		model.addAttribute("type", entity.getType());
		Map typeMap = AdType.getMap();
		model.addAttribute("typeMap", typeMap);
		//上架APP
		
				
				List<PushApp> availPushApps =  pushAppManager.findAbleApp();
				
				model.addAttribute("availPushApps", availPushApps);

		return "/ad/add"+entity.getType();
		
	}
    
    
	
    @RequestMapping(value="/ad/edit",method=RequestMethod.GET)	
	public String toedit(String code ,ModelMap  model,HttpSession session) {
		
    	Ad entity =adManager.getByPk(code);
		if(null==entity){
			  session.setAttribute(Constants.ERR_MESSAGE,"对象不存在code:"+code);
			
		}
		model.addAttribute("entity", entity);
    	
		model.addAttribute("type", entity.getType());
	
		
		//上架APP
		List<PushApp> availPushApps =  pushAppManager.findAbleApp();
	
		model.addAttribute("availPushApps", availPushApps);
		return "/ad/edit"+entity.getType();
		
	}
    
    @RequestMapping(value="/ad/edit",method=RequestMethod.POST)	
   	public String edit(Ad entity,HttpSession session,ModelMap  model) {
   		
   		try{
   				Ad old = adManager.getByPk(entity.getCode());
   				
   				String channelArr = entity.getChannelArr();
   				if(StringUtils.hasText(channelArr)){
   					old.setChannels(ConvertTools.commaString2JsonStr(channelArr));
   				}
   				
   				old.setAdname(entity.getAdname());
   				old.setShowTime(entity.getShowTime());
   				old.setEndTime(entity.getEndTime());
   				
   				old.setSlogan(entity.getSlogan());
   				old.setImg(entity.getImg());
   				
   				old.setResponsemansMax(entity.getResponsemansMax());
   				old.setShowmansMax(entity.getShowmansMax());
   				old.setInstallmansMax(entity.getInstallmansMax()); 
   				old.setActivemansMax(entity.getActivemansMax());
   				
   				adManager.update(old);
   				session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
   				return "redirect:/ad?type="+entity.getType();

   		}catch(DataIntegrityViolationException ce){
   			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:对象已经存在");
   		}catch(Exception e){
   			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:"+e.getMessage());
   			e.printStackTrace();
   		}
   		
   		model.addAttribute("entity", entity);
   		model.addAttribute("type", entity.getType());
   		
   		//上架APP
   		
   		List<PushApp> availPushApps =  pushAppManager.findAbleApp();
		model.addAttribute("availPushApps", availPushApps);

   		return "/ad/edit"+entity.getType();
   		
   	}
    @RequestMapping(value="/ad/ajaxInvalid")	
 	public void ajaxInvalid(String code,HttpServletResponse response) throws IOException  {
    	response.setContentType("text/html");
 		try{
 				Ad entity = adManager.getByPk(code);
 				entity.setState(AdStateType.DOWN.getValue());
 				adManager.update(entity);
 				
 				response.getWriter().write("success");

 		}catch(Exception e){
 			
 			String message =getCause(e).getMessage();
			String errmsg =message;
			response.getWriter().write(errmsg);
 		}
 		
 		
 		
 	}
    
    @RequestMapping(value="/ad/ajaxValid")	
 	public void ajaxValid(String code,HttpServletResponse response) throws IOException  {
    	response.setContentType("text/html");
 		try{
 				Ad entity = adManager.getByPk(code);
 				if(entity.getEndTime().before(new Date())){
 					throw new RuntimeException("请修改广告时间段");
 				}
 				entity.setState(AdStateType.UP.getValue());
 				adManager.update(entity);
 				
 				response.getWriter().write("success");

 		}catch(Exception e){
 			
 			String message =getCause(e).getMessage();
			String errmsg =message;
			response.getWriter().write(errmsg);
 		}
 		
 		
 		
 	}
	


}
