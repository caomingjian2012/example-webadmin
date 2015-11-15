package com.palmtech.ad.web.mk;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
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

import com.common.plugins.myframework.BaseRestSpringController;
import com.common.plugins.spring.mvc.Constants;
import com.common.plugins.spring.mvc.TimeEditor;
import com.palmtech.ad.entity.mk.CpsProfit;
import com.palmtech.ad.entity.mk.CpsProfitQuery;
import com.palmtech.ad.manager.ApkManager;
import com.palmtech.ad.manager.CpsProfitManager;

/**
 * CPS收益管理
 * @author Administrator
 *
 */
@Controller
public class CpsProfitController extends BaseRestSpringController<CpsProfit, Integer> {
	
	public static Logger logger = Logger.getLogger(CpsProfitController.class);
	
 
	@Autowired
	CpsProfitManager cpsProfitManager;
	
	@Autowired
	ApkManager apkManager;

	@InitBinder
	 public void initDataBinder(WebDataBinder binder) {
		 TimeEditor timeEditor = new TimeEditor();
	        timeEditor.setFormat("yyyy-MM-dd");
	        binder.registerCustomEditor(Date.class, timeEditor);
	        
	      
	    }
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/cpsProfit")
	@RequiresRoles("admin")
	public String query(ModelMap model,CpsProfitQuery query) {
		if(StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" calendar desc ");
		}
	
		
		Page<CpsProfit> page = cpsProfitManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		Map total = cpsProfitManager.findSum(query);
		model.addAttribute("total", total);
		return "/cpsProfit/list";
	}
	
	
	@RequestMapping(value="/cpsProfit/add" , method= RequestMethod.GET)
	@RequiresRoles("admin")
	public String toadd(ModelMap model,HttpSession session) {
		
			CpsProfit entity = new CpsProfit();
			entity.setCalendar(DateUtils.addDays(new Date(), -1));

			
			return "/cpsProfit/add";
		
	}
	
	@RequestMapping(value="/cpsProfit/add",method=RequestMethod.POST)
	@RequiresRoles("admin")
	public String add(ModelMap model,CpsProfit entity,HttpSession session) {
		
		
		try{
			 
				
				
			cpsProfitManager.save(entity);
				session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
				return "redirect:/cpsProfit";
			
		}catch(DataIntegrityViolationException ce){
			ce.printStackTrace();
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息：对象已经存在");
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息:"+e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("entity", entity);
		 
		return "/cpsProfit/add";
	}
	
	
	
	
	
	
	@RequestMapping(value="/cpsProfit/edit",method=RequestMethod.GET)
	public String toedit(ModelMap model,CpsProfit entity,HttpSession session) {

			entity = cpsProfitManager.getByPk(entity.getId());
			model.addAttribute("entity", entity);
		
			return "/cpsProfit/edit";
		
		
	}
	
	@RequestMapping(value="/cpsProfit/edit",method=RequestMethod.POST)
	public String edit(ModelMap model,CpsProfit entity,Integer marketchannelId,HttpSession session) {
		
		
		CpsProfit old = null;
		try{
				
			 old =cpsProfitManager.getByPk(entity.getId());	
			 old.setMarketProfit(entity.getMarketProfit());
			 cpsProfitManager.update(old);
			session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
			return "redirect:/cpsProfit";
			
		}catch(DataIntegrityViolationException ce){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息：记录已经存在");
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息："+e.getMessage());
			e.printStackTrace();
		}
		 
		model.addAttribute("entity", old);
		return "/cpsProfit/edit";
	}

	

	
}
