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
import com.palmtech.ad.entity.ctr.WhiteList;
import com.palmtech.ad.entity.ctr.WhiteListQuery;
import com.palmtech.ad.manager.WhiteListManager;

/**
 * 黑名单管理
 * @author Administrator
 *
 */
@Controller
public class WhiteListController extends BaseRestSpringController<WhiteList, Integer> {
	

	@Autowired
	WhiteListManager whiteListManager;
	

	
	
	@RequestMapping("/whiteList")
	public String query(ModelMap  model,WhiteListQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		Page<WhiteList> page = whiteListManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/whiteList/list" ;
	}
	
	@RequestMapping(value="/whiteList/add",method=RequestMethod.GET)	
	public String toadd(ModelMap  model) {
		
		return "/whiteList/add";
		
	}
    @RequestMapping(value="/whiteList/add",method=RequestMethod.POST)	
	public String add(WhiteList entity,HttpSession session,ModelMap  model) {
		
		try{
				whiteListManager.save(entity);
				session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
				return "redirect:/whiteList";

		}catch(DataIntegrityViolationException ce){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:对象已经存在");
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:"+e.getMessage());
			e.printStackTrace();
		}
		
		model.addAttribute("entity", entity);

		return "/whiteList/add";
		
	}
    
    @RequestMapping(value="/whiteList/ajaxadd",method=RequestMethod.POST)	
 	public void ajaxadd(WhiteList entity,HttpServletResponse response) throws IOException  {
    	response.setContentType("text/html");
 		try{
 				whiteListManager.save(entity);
 				
 				response.getWriter().write("success");

 		}catch(Exception e){
 			
 			String message =getCause(e).getMessage();
			String errmsg =message;
			response.getWriter().write(errmsg);
 		}
 		
 		
 		
 	}
	
	
	@RequestMapping("/whiteList/del")
	public void del(Integer id, HttpServletResponse response)  throws IOException {
		response.setContentType("text/json");
		try {
			whiteListManager.deleteByPk(id);			
			response.getWriter().write("success");
			return;
		} catch (Exception e) {
			String message =getCause(e).getMessage();
			response.getWriter().write(message);
		}
		
		
	}
	


}
