package com.palmtech.ad.web.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.org.rapid_framework.page.Page;

import com.common.plugins.myframework.BaseRestSpringController;
import com.palmtech.ad.entity.act.AdBuiltIn;
import com.palmtech.ad.entity.act.AdBuiltInQuery;
import com.palmtech.ad.manager.AdBuiltInManager;

/**
 * 客户端管理
 * @author Administrator
 *
 */
@Controller
public class AdBuiltInController extends BaseRestSpringController<AdBuiltIn, Long> {
	

	@Autowired
	AdBuiltInManager adBuiltInManager;
	

	
	
	@RequestMapping("/adBuiltIn")
	public String query(ModelMap  model,AdBuiltInQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		Page<AdBuiltIn> page = adBuiltInManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/adBuiltIn/list" ;
	}
	
}
 