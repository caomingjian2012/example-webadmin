package com.palmtech.ad.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.org.rapid_framework.page.Page;

import com.common.plugins.myframework.BaseRestSpringController;
import com.palmtech.ad.entity.ad.Mobile;
import com.palmtech.ad.entity.ad.MobileQuery;
import com.palmtech.ad.manager.MobileManager;

/**
 * 手机管理
 * @author Administrator
 *
 */
@Controller
public class MobileController extends BaseRestSpringController<Mobile, String> {
	

	@Autowired
	MobileManager mobileManager;
	

	
	
	@RequestMapping("/mobile")
	public String query(ModelMap  model,MobileQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		Page<Mobile> page = mobileManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/mobile/list" ;
	}
	
}
 