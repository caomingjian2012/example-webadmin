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
import com.palmtech.ad.entity.st.StPushApp;
import com.palmtech.ad.entity.st.StPushAppQuery;
import com.palmtech.ad.manager.PushAppManager;
import com.palmtech.ad.manager.StPushAppManager;

/**
 * 推广APP每日统计
 * @author Administrator
 *
 */
@Controller
public class StPushAppController extends BaseRestSpringController<StPushApp, Long> {
	

	@Autowired
	StPushAppManager stPushAppManager;
	
	@InitBinder
	 public void initDataBinder(WebDataBinder binder) {
	        TimeEditor timeEditor = new TimeEditor();
	        timeEditor.setFormat("yyyy-MM-dd");
	        binder.registerCustomEditor(Date.class, "calendarBegin", timeEditor);
	        binder.registerCustomEditor(Date.class, "calendarEnd", timeEditor);
	      
	    }
	@Autowired
	PushAppManager pushAppManager;
	@RequestMapping("/stat/stPushApp")
	public String query(ModelMap  model,StPushAppQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" appCd desc ");
		}
		Page<StPushApp> page = stPushAppManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		Map<String, PushApp> pushAppMap = pushAppManager.findAll2Map();
		model.addAttribute("pushAppMap", pushAppMap);
		return "/stat/stPushApp" ;
	}
	
}
 