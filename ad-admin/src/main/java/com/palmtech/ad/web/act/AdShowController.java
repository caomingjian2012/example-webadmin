package com.palmtech.ad.web.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.org.rapid_framework.page.Page;

import com.common.plugins.myframework.BaseRestSpringController;
import com.palmtech.ad.entity.act.AdShow;
import com.palmtech.ad.entity.act.AdShowQuery;
import com.palmtech.ad.manager.AdShowManager;

/**
 * 客户端管理
 * @author Administrator
 *
 */
@Controller
public class AdShowController extends BaseRestSpringController<AdShow, Long> {
	

	@Autowired
	AdShowManager adShowManager;
	

	
	
	@RequestMapping("/adShow")
	public String query(ModelMap  model,AdShowQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		Page<AdShow> page = adShowManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/adShow/list" ;
	}
	
}
 