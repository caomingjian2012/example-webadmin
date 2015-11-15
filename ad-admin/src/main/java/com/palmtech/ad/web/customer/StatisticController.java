package com.palmtech.ad.web.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 渠道/销售 后台统计
 * @author Administrator
 *
 */
@Controller("channelStatisController")
@RequestMapping(value={"/channel/statistic","/seller/statistic"})
public class StatisticController {
	
	
//
//	@Autowired
//	UserRelationStatisticManager userRelationStatisticManager;
//	
//	@Autowired
//	UserManager userManager;
//	
//	@Autowired
//	UserApkManager userChannelManager;
//	
//	@Autowired
//	ApkManager channelManager;
//	
//	
//	
//	
//
//	/**
//	 * 用户关联数据日统计
//	 * @param request
//	 * @param session
//	 * @return
//	 * @throws ParseException 
//	 */
//	@RequestMapping("/clientDaily")
//	public String clientDaily(@RequestParam(required =false)Date beginTime,@RequestParam(required =false)Date endTime,@RequestParam(required =false) Integer marketchannelId,HttpSession session,ModelMap model)   {
//		
//		//处理时间
//		
//		if(null==beginTime){
//			
//			beginTime =DateUtils.addDays(DateUtils.truncate(new Date(), Calendar.DATE), -6);
//		}
//		model.addAttribute("beginTime", beginTime);
//		if(null==endTime){
//			endTime =DateUtils.addDays(beginTime, 7);
//		}
//		model.addAttribute("endTime", endTime);
//		model.addAttribute("marketchannelId", marketchannelId);
//		//处理用户相关的推广渠道
//		String userName = SecurityUtils.getSubject().getPrincipal().toString();
//		User user =userManager.findByUsername(userName);
//		// 查用户相关推广渠道
//		UserApkQuery query = new UserApkQuery();
//		query.setUserId(user.getId());;
//		
//		List<UserApk>  userchannels = userChannelManager.findAll(query);
//		List<MarketChannel> marketChannels =new ArrayList<MarketChannel>();
//		List<Integer> marketChannelIds =new ArrayList<Integer>();
//		
//		for(UserApk o:userchannels){
//			
//			marketChannels.add(o.getMarketChannel());
//		
//			marketChannelIds.add(o.getMarketChannel().getId());
//		}
//		model.addAttribute("marketChannels", marketChannels);
//		//查用户相关APK包
//		ApkQuery channelQuery = new ApkQuery();channelQuery.setMarketChannelIds(StringUtils.arrayToCommaDelimitedString(marketChannelIds.toArray()));
//		List<Apk> channels = channelManager.findAll(channelQuery);
//		List<String> channelStr = new ArrayList<String>();
//		for(Apk o :channels){
//			channelStr.add("'"+o.getChannel()+"'");
//		}
//		
//		
//		try{
//			//获取数据
//			List<Map> list = userRelationStatisticManager.userRelationMarketChannelDaily(beginTime, endTime,  StringUtils.arrayToCommaDelimitedString(channelStr.toArray()),marketchannelId, StringUtils.arrayToCommaDelimitedString(marketChannelIds.toArray()));
//			model.addAttribute("list", list);
//			
//			
//			//区间汇总
//			Map<String,Object> rangetotal = userRelationStatisticManager.userRelationMarketChannelSum(beginTime, endTime,  StringUtils.arrayToCommaDelimitedString(channelStr.toArray()),marketchannelId, StringUtils.arrayToCommaDelimitedString(marketChannelIds.toArray()));
//			model.addAttribute("rangetotal",rangetotal);
//		
//		} catch (Exception e) {
//			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息: "+e.getMessage());
//			e.printStackTrace();
//		}
//		
//
//		return "/channel/statistic/clientDaily";
//	}
//
//	
	

}
