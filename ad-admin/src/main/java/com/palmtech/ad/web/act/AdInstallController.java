package com.palmtech.ad.web.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.org.rapid_framework.page.Page;

import com.common.plugins.myframework.BaseRestSpringController;
import com.palmtech.ad.entity.act.AdInstall;
import com.palmtech.ad.entity.act.AdInstallQuery;
import com.palmtech.ad.manager.AdInstallManager;

/**
 * 客户端管理
 * @author Administrator
 *
 */
@Controller
public class AdInstallController extends BaseRestSpringController<AdInstall, Long> {
	

	@Autowired
	AdInstallManager adInstallManager;
	

	
	
	@RequestMapping("/adInstall")
	public String query(ModelMap  model,AdInstallQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		Page<AdInstall> page = adInstallManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/adInstall/list" ;
	}
	
}
 