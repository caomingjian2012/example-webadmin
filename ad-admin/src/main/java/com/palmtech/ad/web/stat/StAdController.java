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
import com.palmtech.ad.entity.ad.Ad;
import com.palmtech.ad.entity.st.StAd;
import com.palmtech.ad.entity.st.StAdQuery;
import com.palmtech.ad.manager.AdManager;
import com.palmtech.ad.manager.StAdManager;

/**
 * 推广APP每日统计
 * @author Administrator
 *
 */
@Controller
public class StAdController extends BaseRestSpringController<StAd, Long> {
	

	@Autowired
	StAdManager stAdManager;
	
	@InitBinder
	 public void initDataBinder(WebDataBinder binder) {
	        TimeEditor timeEditor = new TimeEditor();
	        timeEditor.setFormat("yyyy-MM-dd");
	        binder.registerCustomEditor(Date.class, "calendarBegin", timeEditor);
	        binder.registerCustomEditor(Date.class, "calendarEnd", timeEditor);
	      
	    }
	@Autowired
	AdManager adManager;
	@RequestMapping("/stat/stAd")
	public String query(ModelMap  model,StAdQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" ad desc ");
		}
		Page<StAd> page = stAdManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		Map<String, Ad> adMap = adManager.findAll2Map();
		model.addAttribute("adMap", adMap);
		return "/stat/stAd" ;
	}
	
}
 