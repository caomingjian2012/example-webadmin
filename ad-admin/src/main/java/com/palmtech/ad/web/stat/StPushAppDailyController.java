package com.palmtech.ad.web.stat;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.org.rapid_framework.page.Page;

import com.common.plugins.myframework.BaseRestSpringController;
import com.common.plugins.spring.mvc.TimeEditor;
import com.palmtech.ad.entity.ad.PushApp;
import com.palmtech.ad.entity.st.StPushAppDaily;
import com.palmtech.ad.entity.st.StPushAppDailyQuery;
import com.palmtech.ad.manager.PushAppManager;
import com.palmtech.ad.manager.StPushAppDailyManager;

/**
 * 推广APP每日统计
 * @author Administrator
 *
 */
@Controller
public class StPushAppDailyController extends BaseRestSpringController<StPushAppDaily, Long> {
	

	@Autowired
	StPushAppDailyManager stPushAppDailyManager;
	
	@InitBinder
	 public void initDataBinder(WebDataBinder binder) {
	        TimeEditor timeEditor = new TimeEditor();
	        timeEditor.setFormat("yyyy-MM-dd");
	        binder.registerCustomEditor(Date.class, "calendarBegin", timeEditor);
	        binder.registerCustomEditor(Date.class, "calendarEnd", timeEditor);
	      
	    }
	@Autowired
	PushAppManager pushAppManager;
	@RequestMapping("/stat/stPushAppDaily")
	public String query(ModelMap  model,StPushAppDailyQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" calendar desc ");
		}
		Page<StPushAppDaily> page = stPushAppDailyManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		

		Map<String, PushApp> pushAppMap = pushAppManager.findAll2Map();
		model.addAttribute("pushAppMap", pushAppMap);
		return "/stat/stPushAppDaily" ;
	}
	
}
 