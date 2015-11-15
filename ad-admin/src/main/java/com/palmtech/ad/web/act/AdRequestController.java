package com.palmtech.ad.web.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.org.rapid_framework.page.Page;

import com.common.plugins.myframework.BaseRestSpringController;
import com.palmtech.ad.entity.act.AdRequest;
import com.palmtech.ad.entity.act.AdRequestQuery;
import com.palmtech.ad.manager.AdRequestManager;

/**
 * 客户端管理
 * @author Administrator
 *
 */
@Controller
public class AdRequestController extends BaseRestSpringController<AdRequest, Long> {
	

	@Autowired
	AdRequestManager adRequestManager;
	

	
	
	@RequestMapping("/adRequest")
	public String query(ModelMap  model,AdRequestQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		Page<AdRequest> page = adRequestManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/adRequest/list" ;
	}
	
}
 