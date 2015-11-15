package com.palmtech.ad.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.plugins.jedis.RedisManager;

@Controller
public class OnlineController {
	@Autowired
	RedisManager redisManager;
	
	@RequestMapping("/online")
	public String query(ModelMap  model,String kw) {
		model.addAttribute("kw", kw);
		String pattern = "*";
		if(StringUtils.hasText(kw)){
			pattern="*"+kw+"*";
		}
		Set<byte[]> set =  redisManager.keys(pattern);
		Set<String> userSet = new TreeSet<String>();
		for(byte[] bytes :set){
			try {
				String key = new String(bytes,"utf8");
				userSet.add(key);
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}
		}
		
		model.addAttribute("userSet", userSet);
		model.addAttribute("count", set.size());
		
		return "/online/list";
	}
	
	
	@RequestMapping("/online/an")
	public String an(ModelMap  model) {
		Set<byte[]> set =  redisManager.keys("*");
		Map<String,Integer> channelMap= new HashMap<String,Integer>();
	
		for(byte[] bytes :set){
			try {
				String key = new String(bytes,"utf8");
				String[] arr = key.split("@");
				if(arr.length !=3){
					continue;
				}
				Integer count=channelMap.get(arr[2]);
				if(null!=count){
					channelMap.put(arr[2], count+1);
				}else{
					channelMap.put(arr[2], 1);
				}
				
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}
		}
		
		model.addAttribute("channelMap", channelMap);
		model.addAttribute("count", set.size());
		return "/online/an";
	}
	
	
	
	

}
