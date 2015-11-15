package com.palmtech.ad.web.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.org.rapid_framework.page.Page;

import com.common.plugins.myframework.BaseRestSpringController;
import com.palmtech.ad.entity.act.AdClick;
import com.palmtech.ad.entity.act.AdClickQuery;
import com.palmtech.ad.manager.AdClickManager;

/**
 * 客户端管理
 * @author Administrator
 *
 */
@Controller
public class AdClickController extends BaseRestSpringController<AdClick, Long> {
	

	@Autowired
	AdClickManager adClickManager;
	

	
	
	@RequestMapping("/adClick")
	public String query(ModelMap  model,AdClickQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		Page<AdClick> page = adClickManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/adClick/list" ;
	}
	
}
 