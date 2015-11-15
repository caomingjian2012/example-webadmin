package com.palmtech.ad.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.org.rapid_framework.page.Page;

import com.common.plugins.myframework.BaseRestSpringController;
import com.palmtech.ad.entity.ad.AdClient;
import com.palmtech.ad.entity.ad.AdClientQuery;
import com.palmtech.ad.manager.AdClientManager;

/**
 * 客户端管理
 * @author Administrator
 *
 */
@Controller
public class AdClientController extends BaseRestSpringController<AdClient, Long> {
	

	@Autowired
	AdClientManager adClientManager;
	

	
	
	@RequestMapping("/adClient")
	@RequiresRoles("admin")
	public String query(ModelMap  model,AdClientQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		Page<AdClient> page = adClientManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/adClient/list" ;
	}
	
}
 