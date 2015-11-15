package com.palmtech.ad.web.customer;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import redis.clients.jedis.Client;

import com.common.plugins.myframework.BaseRestSpringController;
import com.common.plugins.myframework.ComplexSqlBean;
import com.common.plugins.spring.mvc.Constants;
import com.common.plugins.spring.mvc.TimeEditor;
import com.palmtech.ad.dao.CpaProfitDao;
import com.palmtech.ad.dao.CpsProfitDao;
import com.palmtech.ad.entity.User;
import com.palmtech.ad.entity.mk.CpaProfit;
import com.palmtech.ad.entity.mk.CpsProfit;
import com.palmtech.ad.entity.mk.UserApk;
import com.palmtech.ad.entity.mk.UserApkQuery;
import com.palmtech.ad.manager.UserApkManager;
import com.palmtech.ad.manager.UserManager;

@Controller
public class ProfitController extends BaseRestSpringController<Client, Integer>{
	
 
	
	@Autowired
	UserApkManager userApkManager;
	@Autowired
	UserManager userManager;
	@Autowired
	CpaProfitDao cpaProfitDao;
	@Autowired
	CpsProfitDao cpsProfitDao;
	
	
	@InitBinder
	 public void initDataBinder(WebDataBinder binder) {
	        TimeEditor timeEditor = new TimeEditor();
	        timeEditor.setFormat("yyyy-MM-dd");
	        binder.registerCustomEditor(Date.class,  timeEditor);
	      
	    }

	@RequestMapping(value={"/cpa/profit"})
	public String cpaProfit(ModelMap model,@RequestParam(required=false)Date calendarBegin,@RequestParam(required=false)Date calendarEnd
			,@RequestParam(required=false)String apk,HttpSession session) {
		
		Date today = new java.sql.Date(System.currentTimeMillis());
		//处理时间
		if(null==calendarBegin){
			calendarBegin = DateUtils.truncate(DateUtils.addDays(new Date(), -6), Calendar.DATE);
		}
		model.addAttribute("calendarBegin", calendarBegin);
		if(null==calendarEnd){
			calendarEnd = DateUtils.addDays(calendarBegin, 7);
		}
		model.addAttribute("calendarEnd", calendarEnd);
		model.addAttribute("apk", apk);
		//处理用户相关的推广渠道
		String userName = SecurityUtils.getSubject().getPrincipal().toString();
		User user =userManager.findByUsername(userName);
		// 查用户相关推广渠道
		UserApkQuery userApkQuery = new UserApkQuery();
		userApkQuery.setUsername(userName);
		List<UserApk> userApks= userApkManager.findAll(userApkQuery);
		model.addAttribute("userApks", userApks);
		
		
		
		try{
			
				List<CpaProfit> list = userRelationCpaProfitDaily(calendarBegin, calendarEnd, apk, userName);
				
				model.addAttribute("list", list);
				int marketProfitSum = userRelationCpaProfitSum(calendarBegin, calendarEnd, apk, userName);
				model.addAttribute("marketProfitSum", marketProfitSum);
		} catch (Exception e) {
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息: "+e.getMessage());
			e.printStackTrace();
		}
		return "/channel/cpa/cpalist";
	}

	
	List<CpaProfit>  userRelationCpaProfitDaily(Date calendarBegin,Date calendarEnd,String apk, String username){
		String sql ="select t.* from cpa_profit as  t join user_apk as  t1 on t.apk = t1.apk join apk as  t2 on t.apk =t2.code"
				+ " where 1=1 /~ and t.calendar >= {calendarBegin} ~/ /~ and t.calendar <= {calendarEnd} ~/ /~ and t2.code = {apk} ~/ /~ and t1.username={username}~/ and t2.type='cpa'  ";
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("apk", apk);
		map.put("username", username);
		map.put("calendarBegin", calendarBegin);
		map.put("calendarEnd", calendarEnd);
		List<CpaProfit> list = cpaProfitDao.sqlFindAll(CpaProfit.class, sql, map);
		return list;
	};
	
	int  userRelationCpaProfitSum(Date calendarBegin,Date calendarEnd,String apk, String username){
		String sql ="select sum(t.marketProfit)  from cpa_profit as  t join user_apk as  t1 on t.apk = t1.apk join apk as  t2 on t.apk =t2.code"
				+ " where 1=1 /~ and t.calendar >= {calendarBegin} ~/ /~ and t.calendar <= {calendarEnd} ~/ /~ and t2.code = {apk} ~/ /~ and t1.username={username}~/ and t2.type='cpa'  ";
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("apk", apk);
		map.put("username", username);
		map.put("calendarBegin", calendarBegin);
		map.put("calendarEnd", calendarEnd);
		int rt = cpaProfitDao.sql4Number( sql, map).intValue();
		return rt;
	};

	
	@RequestMapping(value={"/cps/profit"})
	public String cpsProfit(ModelMap model,@RequestParam(required=false)Date calendarBegin,@RequestParam(required=false)Date calendarEnd
			,@RequestParam(required=false)String apk,HttpSession session) {
		
		Date today = new java.sql.Date(System.currentTimeMillis());
		//处理时间
		if(null==calendarBegin){
			calendarBegin = DateUtils.truncate(DateUtils.addDays(new Date(), -6), Calendar.DATE);
		}
		model.addAttribute("calendarBegin", calendarBegin);
		if(null==calendarEnd){
			calendarEnd = DateUtils.addDays(calendarBegin, 7);
		}
		model.addAttribute("calendarEnd", calendarEnd);
		model.addAttribute("apk", apk);
		//处理用户相关的推广渠道
		String userName = SecurityUtils.getSubject().getPrincipal().toString();
		User user =userManager.findByUsername(userName);
		// 查用户相关推广渠道
		UserApkQuery userApkQuery = new UserApkQuery();
		userApkQuery.setUsername(userName);
		List<UserApk> userApks= userApkManager.findAll(userApkQuery);
		model.addAttribute("userApks", userApks);
		
		
		
		try{
			
				List<Map> list = userRelationCpsProfitDaily(calendarBegin, calendarEnd, apk, userName);
				
				model.addAttribute("list", list);
				int marketProfitSum = userRelationCpsProfitSum(calendarBegin, calendarEnd, apk, userName);
				model.addAttribute("marketProfitSum", marketProfitSum);
				int mobielSum = userRelationCpsMobileSum(calendarBegin, calendarEnd, apk, userName);
				model.addAttribute("mobielSum", mobielSum);
		} catch (Exception e) {
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息: "+e.getMessage());
			e.printStackTrace();
		}
		return "/channel/cps/cpslist";
	}

	
	List<Map>  userRelationCpsProfitDaily(Date calendarBegin,Date calendarEnd,String apk, String username){
//		String sql ="select t.*, from cps_profit as  t "
//				+ " join user_apk as  t1 on t.apk = t1.apk "
//				+ "  join apk as  t2 on t.apk =t2.code "
//				+ " where 1=1 /~ and t.calendar >= {calendarBegin} ~/ /~ and t.calendar <= {calendarEnd} ~/ "
//				+ " /~ and t2.code = {apk} ~/ /~ and t1.username={username}~/ and t2.type='cps'  ";
		
		Assert.notNull(calendarBegin,"开始时间不能为空");
		Assert.notNull(calendarEnd,"结束时间不能为空");
		Assert.isTrue(calendarEnd.after(calendarBegin),"开始时间不能大于结束时间");
		Assert.hasText(username,"用户名必须有");
	
		String statAdSql = ComplexSqlBean.getSql("userCpsDailySql");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("apk", apk);
		map.put("username", username);
		map.put("calendarBegin", calendarBegin);
		map.put("calendarEnd", calendarEnd);
		List<Map> list = cpsProfitDao.sqlFindAll( statAdSql, map);
		return list;
	};
	
	int  userRelationCpsProfitSum(Date calendarBegin,Date calendarEnd,String apk, String username){
		String sql ="select sum(t.marketProfit)  from cps_profit as  t join user_apk as  t1 on t.apk = t1.apk join apk as  t2 on t.apk =t2.code"
				+ " where 1=1 /~ and t.calendar >= {calendarBegin} ~/ /~ and t.calendar <= {calendarEnd} ~/ /~ and t2.code = {apk} ~/ /~ and t1.username={username}~/ and t2.type='cps'  ";
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("apk", apk);
		map.put("username", username);
		map.put("calendarBegin", calendarBegin);
		map.put("calendarEnd", calendarEnd);
		int rt = cpsProfitDao.sql4Number( sql, map).intValue();
		return rt;
	};
	int  userRelationCpsMobileSum(Date calendarBegin,Date calendarEnd,String apk, String username){
		String sql ="select sum(t.mobiles)  from adst.st_apk_daily as  t join user_apk as  t1 on t.apkName = t1.apk join apk as  t2 on t.apkName =t2.code"
				+ " where 1=1 /~ and t.calendar >= {calendarBegin} ~/ /~ and t.calendar <= {calendarEnd} ~/ /~ and t2.code = {apk} ~/ /~ and t1.username={username}~/ and t2.type='cps'  ";
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("apk", apk);
		map.put("username", username);
		map.put("calendarBegin", calendarBegin);
		map.put("calendarEnd", calendarEnd);
		int rt = cpsProfitDao.sql4Number( sql, map).intValue();
		return rt;
	};
	



}


