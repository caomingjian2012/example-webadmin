package com.palmtech.ad.web.stat;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.plugins.spring.mvc.Constants;
import com.common.plugins.spring.mvc.TimeEditor;
import com.palmtech.ad.dao.AdClientDao;
import com.palmtech.ad.dao.ApkDao;
import com.palmtech.ad.entity.ad.AdQuery;
import com.palmtech.ad.entity.ad.Apk;
import com.palmtech.ad.entity.ad.ApkQuery;
import com.palmtech.ad.entity.ad.PushAppQuery;
import com.palmtech.ad.manager.StatManager;

/**
 * 统计管理
 * @author Administrator
 *
 */
@Controller
public class StatsConntroller {
	
	@Autowired
	StatManager statManager;
	@Autowired
	AdClientDao appUserDao;
	@Autowired
	ApkDao apkDao;
	
	@InitBinder
	 public void initDataBinder(WebDataBinder binder) {
	        TimeEditor timeEditor = new TimeEditor();
	        timeEditor.setFormat("yyyy-MM-dd");
	        binder.registerCustomEditor(Date.class, "calendarBegin", timeEditor);
	        binder.registerCustomEditor(Date.class, "calendarEnd", timeEditor);
	        binder.registerCustomEditor(Date.class, "begin", timeEditor);
	        binder.registerCustomEditor(Date.class, "end", timeEditor);
	        
	    }
	
	/**
	 * 某个广告效果统计
	 * @param adCd
	 * @return
	 */
	@RequestMapping("/stat/ad")
	public String ad(AdQuery query,ModelMap model,HttpSession session){
		
		
		model.addAttribute("query", query);
		if(null == query.getCalendarBegin()){
			Date begin =DateUtils.truncate(new Date(), Calendar.DATE);
			query.setCalendarBegin(begin); 
		}
		if(null == query.getCalendarEnd()){		
			
					Date end =DateUtils.addDays(DateUtils.truncate(query.getCalendarBegin(), Calendar.DATE), 1);
					query.setCalendarEnd(end); 
		}
		
	
		try{
			List<Map> list = statManager.statAd(query.getAdcd(), query.getCalendarBegin(), query.getCalendarEnd());
			model.addAttribute("list",list);
		} catch (Exception e) {
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息: "+e.getMessage());
			e.printStackTrace();
		}
		return "/stat/ad";
	}
	
	/**
	 * 某个APP推广效果统计
	 * @param adCd
	 * @return
	 */
	@RequestMapping("/stat/app")
	public String app(PushAppQuery query,ModelMap model,HttpSession session){
		
		
		model.addAttribute("query", query);
		if(null == query.getCalendarBegin()){
			Date begin =DateUtils.truncate(new Date(), Calendar.DATE);
			query.setCalendarBegin(begin); 
		}
		if(null == query.getCalendarEnd()){		
			
					Date end =DateUtils.addDays(DateUtils.truncate(query.getCalendarBegin(), Calendar.DATE), 1);
					query.setCalendarEnd(end); 
		}
		try{
			List<Map> list = statManager.statApp(query.getAdcd(), query.getCalendarBegin(), query.getCalendarEnd());
			model.addAttribute("list",list);
		} catch (Exception e) {
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息: "+e.getMessage());
			e.printStackTrace();
		}
		return "/stat/app";
	}
	

	@RequestMapping("/stat/channel")
	public String query(ModelMap  model) {
		
		List<Map> list = statManager.statChannel();
		model.addAttribute("list", list);
		
		return "/stat/channel" ;
	}
	
    @RequestMapping("/stat/feechanneldaily")
	public String daily(ModelMap  model,ApkQuery query) {
    	model.addAttribute("query", query);
    	if(null == query.getCalendarBegin()){							
			
    		Date begin = DateUtils.addDays(DateUtils.truncate(new Date(), Calendar.DATE), -7);
    		query.setCalendarBegin(begin); 
    	}
		if(null == query.getCalendarEnd()){		
			Date end =DateUtils.addDays(DateUtils.truncate(query.getCalendarBegin(), Calendar.DATE), 8);
			query.setCalendarEnd(end); 
		}
		
	
    	
    	HashMap<String,Object> map = new HashMap<String,Object>();
    	map.put("begin", query.getCalendarBegin());
    	map.put("end", query.getCalendarEnd());
    	map.put("feechannel", query.getCode());
		String sql = "select count(1) as mans,u_channel as feechannel,DATE_FORMAT(u_createTime,'%Y-%m-%d') as calendar from t_user where 1=1 /~ and u_createTime >={begin} ~//~ and u_createTime <{end} ~/ /~ and u_channel = {feechannel} ~/  group by u_channel ,calendar order by calendar desc ";
		List<Map> list = appUserDao.sqlFindAll(sql, map);
		
		model.addAttribute("list", list);
		Map blackMap = getpingbiyonghu();
		model.addAttribute("blackMap", blackMap);
		Map leijiMap = getleijiyonghu();
		model.addAttribute("leijiMap", leijiMap);
		Map feechannelMap = getFeechannels();
		model.addAttribute("feechannelMap", feechannelMap);
		return "/stat/feechanneldaily" ;
	}
    
    Map<String,Number> getpingbiyonghu(){
    	String sql1= "select count(t2.u_id)  as blacks ,u_channel as feechannel from t_blacklist  as t1 join t_user as  t2  on t1.uuid = t2.u_uuid group by u_channel ";
		List<Map> list2 = appUserDao.sqlFindAll(sql1, new HashMap<String,Object>());
		Map<String,Number> blackMap= new HashMap<String,Number>();
		for (Map m :list2) {
			String key = m.get("feechannel").toString();
			
			Number v = (Number)m.get("blacks");
			blackMap.put(key, v);
		}
		return blackMap;
    }
    
    Map<String,Number> getleijiyonghu(){
    	String sql1= "select count(1) as mans,u_channel as feechannel from t_user group by u_channel";
		List<Map> list2 = appUserDao.sqlFindAll(sql1, new HashMap<String,Object>());
		Map<String,Number> leijiMap= new HashMap<String,Number>();
		for (Map m :list2) {
			if( m.get("feechannel")==null){
				continue;
			}
			String key = m.get("feechannel").toString();
			
			Number v = (Number)m.get("mans");
			leijiMap.put(key, v);
		}
		return leijiMap;
    }
    Map<String,Apk> getFeechannels(){
    	
		List<Apk> list2 = apkDao.findAll("");
		Map<String,Apk> leijiMap= new HashMap<String,Apk>();
		for (Apk m :list2) {
			String key = m.getCode();
			
			
			leijiMap.put(key, m);
		}
		return leijiMap;
    }
}
