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
import com.palmtech.ad.entity.ad.Apk;
import com.palmtech.ad.entity.st.StApkDaily;
import com.palmtech.ad.entity.st.StApkDailyQuery;
import com.palmtech.ad.manager.ApkManager;
import com.palmtech.ad.manager.StApkDailyManager;

/**
 * 客户端管理
 * @author Administrator
 *
 */
@Controller
public class StApkDailyController extends BaseRestSpringController<StApkDaily, Long> {
	

	@Autowired
	StApkDailyManager stApkDailyManager;
	
	@InitBinder
	 public void initDataBinder(WebDataBinder binder) {
	        TimeEditor timeEditor = new TimeEditor();
	        timeEditor.setFormat("yyyy-MM-dd");
	        binder.registerCustomEditor(Date.class, "calendarBegin", timeEditor);
	        binder.registerCustomEditor(Date.class, "calendarEnd", timeEditor);
	      
	    }
	@Autowired
	ApkManager apkManager;
	@RequestMapping("/stat/stApkDaily")
	public String query(ModelMap  model,StApkDailyQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" calendar desc ");
		}
		Page<StApkDaily> page = stApkDailyManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		Map<String, Apk> apkMap = apkManager.findAll2Map();
		model.addAttribute("apkMap", apkMap);
		return "/stat/stApkDaily" ;
	}
	
}
 