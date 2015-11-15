package com.palmtech.ad.web.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.org.rapid_framework.page.Page;

import com.common.plugins.myframework.BaseRestSpringController;
import com.palmtech.ad.entity.act.AdDownload;
import com.palmtech.ad.entity.act.AdDownloadQuery;
import com.palmtech.ad.manager.AdDownloadManager;

/**
 * 客户端管理
 * @author Administrator
 *
 */
@Controller
public class AdDownloadController extends BaseRestSpringController<AdDownload, Long> {
	

	@Autowired
	AdDownloadManager adDownloadManager;
	

	
	
	@RequestMapping("/adDownload")
	public String query(ModelMap  model,AdDownloadQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		Page<AdDownload> page = adDownloadManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/adDownload/list" ;
	}
	
}
 