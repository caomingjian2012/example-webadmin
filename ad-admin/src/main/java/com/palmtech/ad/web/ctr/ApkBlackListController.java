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
import com.palmtech.ad.entity.ctr.ApkBlackList;
import com.palmtech.ad.entity.ctr.ApkBlackListQuery;
import com.palmtech.ad.manager.ApkBlackListManager;

/**
 * APK黑名单管理
 * @author Administrator
 *
 */
@Controller
public class ApkBlackListController extends BaseRestSpringController<ApkBlackList, Integer> {
	

	@Autowired
	ApkBlackListManager apkBlackListManager;

	
	
	@RequestMapping("/apkBlackList")
	public String query(ModelMap  model,ApkBlackListQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		
		
		Page<ApkBlackList> page = apkBlackListManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/apkBlackList/list" ;
	}
	
	

	
	@RequestMapping(value="/apkBlackList/add",method=RequestMethod.GET)	
	public String toadd(ModelMap  model) {
		
		return "/apkBlackList/add";
		
	}
    @RequestMapping(value="/apkBlackList/add",method=RequestMethod.POST)	
	public String add(ApkBlackList entity,HttpSession session,ModelMap  model) {
		
		try{
			apkBlackListManager.save(entity);
				session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
				return "redirect:/apkBlackList";

		}catch(DataIntegrityViolationException ce){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:对象已经存在");
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:"+e.getMessage());
			e.printStackTrace();
		}
		
		model.addAttribute("entity", entity);

		return "/apkBlackList/add";
		
	}
    
    @RequestMapping(value="/apkBlackList/ajaxadd",method=RequestMethod.POST)	
 	public void ajaxadd(ApkBlackList entity,HttpServletResponse response) throws IOException  {
    	response.setContentType("text/html");
 		try{
 			apkBlackListManager.save(entity);
 				
 				response.getWriter().write("success");

 		}catch(Exception e){
 			
 			String message =getCause(e).getMessage();
			String errmsg =message;
			response.getWriter().write(errmsg);
 		}
 		
 		
 		
 	}
	
	
	@RequestMapping("/apkBlackList/del")
	public void del(Integer id, HttpServletResponse response)  throws IOException {
		response.setContentType("text/json");
		try {
			apkBlackListManager.deleteByPk(id);			
			response.getWriter().write("success");
			return;
		} catch (Exception e) {
			String message =getCause(e).getMessage();
			response.getWriter().write(message);
		}
		
		
	}
	


}
