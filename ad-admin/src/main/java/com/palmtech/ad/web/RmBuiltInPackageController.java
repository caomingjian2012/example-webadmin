package com.palmtech.ad.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.palmtech.ad.entity.RmBuiltInPackage;
import com.palmtech.ad.entity.RmBuiltInPackageQuery;
import com.palmtech.ad.entity.ctr.BlackList;
import com.palmtech.ad.manager.RmBuiltInPackageManager;

/**
 * 黑名单管理
 * @author Administrator
 *
 */
@Controller
public class RmBuiltInPackageController extends BaseRestSpringController<RmBuiltInPackage, Integer> {
	

	@Autowired
	RmBuiltInPackageManager rmBuiltInPackageManager;
	

	
	
	@RequestMapping("/rmBuiltInPackage")
	public String query(ModelMap  model,RmBuiltInPackageQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		Page<RmBuiltInPackage> page = rmBuiltInPackageManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/rmBuiltInPackage/list" ;
	}
	
	@RequestMapping(value="/rmBuiltInPackage/add",method=RequestMethod.GET)	
	public String toadd(ModelMap  model) {
		
		return "/rmBuiltInPackage/add";
		
	}
	 @RequestMapping(value="/rmBuiltInPackage/ajaxadd",method=RequestMethod.POST)	
	 	public void ajaxadd(String packageName,String name,HttpServletResponse response) throws IOException  {
	    	response.setContentType("text/html");
	 		try{
	 			
	 				RmBuiltInPackage entity = new RmBuiltInPackage();
	 				if(!StringUtils.hasText(packageName)) {
	 					throw new  RuntimeException("packageName不能为空");
	 				}
	 				if(!StringUtils.hasText(name)) {
	 					throw new  RuntimeException("name不能为空");
	 				}	
	 				entity.setPackageName(packageName);
	 				entity.setName(name);
	 				
	 				
	 				entity.setCreateTime(new Date());
	 				entity.setStatus("Y");
	 				rmBuiltInPackageManager.save(entity);
	 				
	 				response.getWriter().write("success");

	 		}catch(Exception e){
	 			
	 			String message =getCause(e).getMessage();
				String errmsg =message;
				response.getWriter().write(errmsg);
	 		}
	 		
	 		
	 		
	 	}
    
   
   
    
   
	
	
	@RequestMapping("/rmBuiltInPackage/del")
	public void del(Integer id, HttpServletResponse response)  throws IOException {
		response.setContentType("text/json");
		try {
			rmBuiltInPackageManager.deleteByPk(id);			
			response.getWriter().write("success");
			return;
		} catch (Exception e) {
			String message =getCause(e).getMessage();
			response.getWriter().write(message);
		}
		
		
	}
	


}
