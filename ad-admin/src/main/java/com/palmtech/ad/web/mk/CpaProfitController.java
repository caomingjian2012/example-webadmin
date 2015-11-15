package com.palmtech.ad.web.mk;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
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

import com.common.plugins.myframework.BaseRestSpringController;
import com.common.plugins.spring.mvc.Constants;
import com.common.plugins.spring.mvc.TimeEditor;
import com.palmtech.ad.entity.mk.CpaProfit;
import com.palmtech.ad.entity.mk.CpaProfitQuery;
import com.palmtech.ad.manager.ApkManager;
import com.palmtech.ad.manager.CpaProfitManager;

/**
 *cpa收益管理
 * @author Administrator
 *
 */
@Controller
public class CpaProfitController extends BaseRestSpringController<CpaProfit, Integer> {
	
	public static Logger logger = Logger.getLogger(CpaProfitController.class);
	
 
	@Autowired
	CpaProfitManager cpaProfitManager;
	
	@Autowired
	ApkManager apkManager;

	@InitBinder
	 public void initDataBinder(WebDataBinder binder) {
		  TimeEditor timeEditor = new TimeEditor();
	        timeEditor.setFormat("yyyy-MM-dd");
	        binder.registerCustomEditor(Date.class, timeEditor);
	       
	        
	      
	    }
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/cpaProfit")
	public String query(ModelMap model,CpaProfitQuery query) {
		if(StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" calendar desc ");
		}
	
		
		Page<CpaProfit> page = cpaProfitManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		Map total = cpaProfitManager.findSum(query);
		model.addAttribute("total", total);
		return "/cpaProfit/list";
	}
	
	
	@RequestMapping(value="/cpaProfit/add" , method= RequestMethod.GET)
	public String toadd(ModelMap model,HttpSession session) {
		
			CpaProfit entity = new CpaProfit();
			entity.setCalendar(DateUtils.addDays(new Date(), -1));

			
			return "/cpaProfit/add";
		
	}
	
	@RequestMapping(value="/cpaProfit/add",method=RequestMethod.POST)
	public String add(ModelMap model,CpaProfit entity,HttpSession session) {
		
		
		try{
			 
				
				
			cpaProfitManager.save(entity);
				session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
				return "redirect:/cpaProfit";
			
		}catch(DataIntegrityViolationException ce){
			ce.printStackTrace();
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息：对象已经存在");
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:"+e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("entity", entity);
		 
		return "/cpaProfit/add";
	}
	
	
	
	
	
	
	@RequestMapping(value="/cpaProfit/edit",method=RequestMethod.GET)
	public String toedit(ModelMap model,CpaProfit entity,HttpSession session) {

			entity = cpaProfitManager.getByPk(entity.getId());
			model.addAttribute("entity", entity);
		
			return "/cpaProfit/edit";
		
		
	}
	
	@RequestMapping(value="/cpaProfit/edit",method=RequestMethod.POST)
	public String edit(ModelMap model,CpaProfit entity,Integer marketchannelId,HttpSession session) {
		
		
		CpaProfit old = null;
		try{
				
			 old =cpaProfitManager.getByPk(entity.getId());		
			 old.setActives(entity.getActives());
			 old.setPrice(entity.getPrice());
		
			 old.setMarketProfit(entity.getMarketProfit());
			
			
			 
			 cpaProfitManager.update(old);
			 
			session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
			return "redirect:/cpaProfit";
			
		}catch(DataIntegrityViolationException ce){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息：记录已经存在");
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息："+e.getMessage());
			e.printStackTrace();
		}
		 
		model.addAttribute("entity", old);
		return "/cpaProfit/edit";
	}

	
//	@RequestMapping(value="/apiservice/ajaxCalProfit",method=RequestMethod.GET)
//	public void calCpaProfit(Date calendar,HttpServletResponse response) throws IOException{
//		response.setContentType("text/json");
//		try {
//			logger.warn("计算calendar:"+calendar);
//			
//			ApkQuery query = new ApkQuery();
//			query.setPageSize(Integer.MAX_VALUE);
//			query.setType("cpa");
//			Page<Apk> page=apkManager.findPage(query);
//			for(Apk apk:page.getResult()){
//				cpaProfitManager.cal(calendar, apk.getCode());
//			}
//			
//			response.getWriter().write("success");
//			return;
//		} catch (Exception e) {
//			e.printStackTrace();
//			String message =getCause(e).getMessage();
//			response.getWriter().write(message);
//		}
//		
//	}
	
}
