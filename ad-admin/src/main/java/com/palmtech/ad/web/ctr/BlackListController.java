package com.palmtech.ad.web.ctr;

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
import com.palmtech.ad.entity.ctr.BlackList;
import com.palmtech.ad.entity.ctr.BlackListQuery;
import com.palmtech.ad.manager.BlackListManager;

/**
 * 黑名单管理
 * @author Administrator
 *
 */
@Controller
public class BlackListController extends BaseRestSpringController<BlackList, Integer> {
	

	@Autowired
	BlackListManager blackListManager;
	

	
	
	@RequestMapping("/blackList")
	public String query(ModelMap  model,BlackListQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		Page<BlackList> page = blackListManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/blackList/list" ;
	}
	
	@RequestMapping(value="/blackList/add",method=RequestMethod.GET)	
	public String toadd(ModelMap  model) {
		
		return "/blackList/add";
		
	}
    @RequestMapping(value="/blackList/add",method=RequestMethod.POST)	
	public String add(BlackList entity,HttpSession session,ModelMap  model) {
		
		try{
				blackListManager.save(entity);
				session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
				return "redirect:/blackList";

		}catch(DataIntegrityViolationException ce){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:对象已经存在");
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:"+e.getMessage());
			e.printStackTrace();
		}
		
		model.addAttribute("entity", entity);

		return "/blackList/add";
		
	}
    
    @RequestMapping(value="/blackList/ajaxadd",method=RequestMethod.POST)	
 	public void ajaxadd(String uuid,String remark,HttpServletResponse response) throws IOException  {
    	response.setContentType("text/html");
 		try{
 			
 				BlackList entity = new BlackList();
 				if(!StringUtils.hasText(uuid)) {
 					throw new  RuntimeException("UUID不能为空");
 				}
 					
 				entity.setUuid(uuid.trim());
 				if(StringUtils.hasText(remark)){
 					entity.setRemark(remark);
 				}else{
 					entity.setRemark(uuid);
 				}
 				
 				entity.setCreateTime(new Date());
 				blackListManager.save(entity);
 				
 				response.getWriter().write("success");

 		}catch(Exception e){
 			
 			String message =getCause(e).getMessage();
			String errmsg =message;
			response.getWriter().write(errmsg);
 		}
 		
 		
 		
 	}
    
    @RequestMapping(value="/blackList/batadd",method=RequestMethod.GET)	
    public String tobatAdd(String uuids,HttpSession session,ModelMap  model){
    	String[] arr = uuids.split(",");
    	List<String> list = new ArrayList<String>();
    	for(String uuid:arr){
    		if(StringUtils.hasText(uuid)){
    			list.add(uuid);
    		}
    	}
    	model.addAttribute("uuids", StringUtils.arrayToDelimitedString(list.toArray(), ","));
    	return  "/blackList/batadd";
    }
    
    @RequestMapping(value="/blackList/ajaxbatadd",method=RequestMethod.POST)	
 	public void ajaxBatadd(String uuids,String remark,HttpServletResponse response) throws IOException  {
    	response.setContentType("text/html");
 		try{
 				String[] uuidArr = uuids.split(",");
 				List<BlackList> list =new ArrayList<BlackList>();
 				for(String uuid:uuidArr){
 					BlackList entity = new BlackList();
 	 				entity.setUuid(uuid);
 	 				entity.setRemark(remark);
 	 				entity.setCreateTime(new Date());
 	 				list.add(entity);
 				}
 				
 			
 				blackListManager.saveAll(list);
 				
 				response.getWriter().write("success");

 		}catch(Exception e){
 			
 			String message =getCause(e).getMessage();
			String errmsg =message;
			response.getWriter().write(errmsg);
 		}
 		
 		
 		
 	}
	
	
	@RequestMapping("/blackList/del")
	public void del(Integer id, HttpServletResponse response)  throws IOException {
		response.setContentType("text/json");
		try {
			blackListManager.deleteByPk(id);			
			response.getWriter().write("success");
			return;
		} catch (Exception e) {
			String message =getCause(e).getMessage();
			response.getWriter().write(message);
		}
		
		
	}
	


}
