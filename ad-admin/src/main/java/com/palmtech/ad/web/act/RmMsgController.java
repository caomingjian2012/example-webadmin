package com.palmtech.ad.web.act;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.org.rapid_framework.page.Page;

import com.common.plugins.myframework.BaseRestSpringController;
import com.common.plugins.spring.mvc.Constants;
import com.palmtech.ad.entity.act.RmMsg;
import com.palmtech.ad.entity.act.RmMsgQuery;
import com.palmtech.ad.manager.RmMsgManager;

/**
 * 黑名单管理
 * @author Administrator
 *
 */
@Controller
public class RmMsgController extends BaseRestSpringController<RmMsg, Integer> {
	

	@Autowired
	RmMsgManager rmMsgManager;
	

	
	
	@RequestMapping("/rmMsg")
	public String query(ModelMap  model,RmMsgQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" createTime desc ");
		}
		Page<RmMsg> page = rmMsgManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		
		return "/rmMsg/list" ;
	}
	

    
 
    


}
