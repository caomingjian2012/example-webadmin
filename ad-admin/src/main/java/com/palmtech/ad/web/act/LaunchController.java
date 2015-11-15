package com.palmtech.ad.web.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.org.rapid_framework.page.Page;

import com.common.plugins.myframework.BaseRestSpringController;
import com.palmtech.ad.entity.act.Launch;
import com.palmtech.ad.entity.act.LaunchQuery;
import com.palmtech.ad.manager.LaunchManager;

/**
 * 客户端启动
 * @author Administrator
 *
 */
@Controller
public class LaunchController extends BaseRestSpringController<Launch, Long> {
	

	@Autowired
	LaunchManager launchManager;
	

	
	
	@RequestMapping("/launch")
	public String query(ModelMap  model,LaunchQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		Page<Launch> page = launchManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/launch/list" ;
	}
	
}
 