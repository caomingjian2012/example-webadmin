package com.palmtech.ad.web.ctr;

import java.io.IOException;

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
import com.palmtech.ad.entity.ctr.ApkWhiteList;
import com.palmtech.ad.entity.ctr.ApkWhiteListQuery;
import com.palmtech.ad.manager.ApkWhiteListManager;

/**
 * APK白名单管理
 * @author Administrator
 *
 */
@Controller
public class ApkWhiteListController extends BaseRestSpringController<ApkWhiteList, Integer> {
	

	@Autowired
	ApkWhiteListManager apkWhiteListManager;

	
	
	@RequestMapping("/apkWhiteList")
	public String query(ModelMap  model,ApkWhiteListQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		
		
		Page<ApkWhiteList> page = apkWhiteListManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/apkWhiteList/list" ;
	}
	
	

	
	@RequestMapping(value="/apkWhiteList/add",method=RequestMethod.GET)	
	public String toadd(ModelMap  model) {
		
		return "/apkWhiteList/add";
		
	}
    @RequestMapping(value="/apkWhiteList/add",method=RequestMethod.POST)	
	public String add(ApkWhiteList entity,HttpSession session,ModelMap  model) {
		
		try{
			apkWhiteListManager.save(entity);
				session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
				return "redirect:/apkWhiteList";

		}catch(DataIntegrityViolationException ce){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:对象已经存在");
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:"+e.getMessage());
			e.printStackTrace();
		}
		
		model.addAttribute("entity", entity);

		return "/apkWhiteList/add";
		
	}
    
    @RequestMapping(value="/apkWhiteList/ajaxadd",method=RequestMethod.POST)	
 	public void ajaxadd(ApkWhiteList entity,HttpServletResponse response) throws IOException  {
    	response.setContentType("text/html");
 		try{
 			apkWhiteListManager.save(entity);
 				
 				response.getWriter().write("success");

 		}catch(Exception e){
 			
 			String message =getCause(e).getMessage();
			String errmsg =message;
			response.getWriter().write(errmsg);
 		}
 		
 		
 		
 	}
	
	
	@RequestMapping("/apkWhiteList/del")
	public void del(Integer id, HttpServletResponse response)  throws IOException {
		response.setContentType("text/json");
		try {
			apkWhiteListManager.deleteByPk(id);			
			response.getWriter().write("success");
			return;
		} catch (Exception e) {
			String message =getCause(e).getMessage();
			response.getWriter().write(message);
		}
		
		
	}
	


}
