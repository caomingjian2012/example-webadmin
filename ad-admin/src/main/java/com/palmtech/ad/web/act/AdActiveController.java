package com.palmtech.ad.web.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.org.rapid_framework.page.Page;

import com.common.plugins.myframework.BaseRestSpringController;
import com.palmtech.ad.entity.act.AdActive;
import com.palmtech.ad.entity.act.AdActiveQuery;
import com.palmtech.ad.manager.AdActiveManager;

/**
 * 客户端管理
 * @author Administrator
 *
 */
@Controller
public class AdActiveController extends BaseRestSpringController<AdActive, Long> {
	

	@Autowired
	AdActiveManager adActiveManager;
	

	
	
	@RequestMapping("/adActive")
	public String query(ModelMap  model,AdActiveQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		Page<AdActive> page = adActiveManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/adActive/list" ;
	}
	
}
 