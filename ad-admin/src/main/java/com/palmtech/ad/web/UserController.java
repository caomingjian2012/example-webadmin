package com.palmtech.ad.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.org.rapid_framework.page.Page;

import com.common.plugins.myframework.BaseRestSpringController;
import com.common.plugins.spring.mvc.Constants;
import com.palmtech.ad.bean.UserRoleVo;
import com.palmtech.ad.entity.Role;
import com.palmtech.ad.entity.User;
import com.palmtech.ad.entity.UserQuery;
import com.palmtech.ad.entity.UserRole;
import com.palmtech.ad.manager.RoleManager;
import com.palmtech.ad.manager.UserApkManager;
import com.palmtech.ad.manager.UserManager;
import com.palmtech.ad.utils.CommonUtil;
/**
 * 后台框架页面
 * @author Administrator
 *
 */
@Controller
public class UserController extends BaseRestSpringController<User, Integer>{

	@Autowired
	UserManager userManager;
	@Autowired
	RoleManager roleManager;
	@Autowired
	UserApkManager userApkManager;
	
	
	@RequestMapping("/user")
	@RequiresPermissions("user:menu")
	public String query(ModelMap model,UserQuery query) {
		
		if(!StringUtils.hasText(query.getSortColumns())){
			query.setSortColumns(" id desc ");
		}
		
		Page<User> page = userManager.findPage(query);
		
		
		model.addAllAttributes(toModelMap(page, query));
		//相关角色
		Map userRolesMap = userManager.findUserRoles();
		model.addAttribute("userRolesMap", userRolesMap);
		//相关推广渠道
				Map userApksMap = userApkManager.findUserApkMap();
				model.addAttribute("userApksMap", userApksMap);
		
		
		
		return "/user/list";
	}

	
	@RequestMapping("/user/add")
	@RequiresPermissions("user:menu")
	public ModelAndView add(User user,String password1, HttpServletRequest request,HttpSession session) {
		if("get".equalsIgnoreCase(request.getMethod())){			
			return new ModelAndView("/user/add");
		}
		try{
			boolean validate =true;
			if(!StringUtils.hasLength(user.getUsername())){
				session.setAttribute(Constants.ERR_MESSAGE, "用户名不能为空");
				validate =false;
			}
			if(!StringUtils.hasLength(user.getPassword())){
				session.setAttribute(Constants.ERR_MESSAGE, "密码不能为空");
				validate =false;
			}
			if(!user.getPassword().equals(password1)){
				session.setAttribute(Constants.ERR_MESSAGE, "密码不一致");
				validate =false;
				
			}
			if(validate){
				user.setPassword(CommonUtil.hash(user.getPassword()));
				userManager.save(user);
				session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
				return new ModelAndView("redirect:/user");
			}
			
			
		}catch(DataIntegrityViolationException ce){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息：对象已经存在");
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息："+e.getMessage());
			e.printStackTrace();
		}
		return new ModelAndView("user/add","user",user);
		
	}
	/**
	 * 修改资料
	 * @param id
	 * @param user
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/user/edit")
	@RequiresPermissions("user:menu")
	public ModelAndView edit(Integer id,User user, HttpServletRequest request,HttpSession session) {
		if("get".equalsIgnoreCase(request.getMethod())){	
			user =userManager.findById(id);
			if(null==user){
				session.setAttribute(Constants.ERR_MESSAGE,"对象不存在User:"+id);
				
			}
			return  new ModelAndView("/user/edit","user",user);
		}
		try{
			User old =userManager.findById(user.getId());
			old.setGender(user.getGender());
			old.setMail(user.getMail());
			old.setName(user.getName());
			old.setTelephone(user.getTelephone());
			userManager.update(old);
			session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
			return new ModelAndView("redirect:/user");
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息："+e.getMessage());
			e.printStackTrace();
			
			
		}
		return new ModelAndView("/user/edit","user",user);
	}
	
	/**
	 * 修改密码
	 * @param id
	 * @param user
	 * @param password1
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/user/editpw")
	@RequiresPermissions("user:menu")
	public ModelAndView editpw(Integer id,User user,String password1, HttpServletRequest request,HttpSession session) {
		if("get".equalsIgnoreCase(request.getMethod())){	
			user =userManager.findById(id);
			if(null==user){
				session.setAttribute(Constants.ERR_MESSAGE,"对象不存在User:"+id);
				
			}
			return  new ModelAndView("/user/editpw","user",user);
		}
		try{
			boolean validate =true;
			
			if(!StringUtils.hasLength(user.getPassword())){
				session.setAttribute(Constants.ERR_MESSAGE, "密码不能为空");
				validate =false;
			}
			if(!user.getPassword().equals(password1)){
				session.setAttribute(Constants.ERR_MESSAGE, "密码不一致");
				validate =false;
				
			}
			if(validate){
				User old =userManager.findById(user.getId());
				old.setPassword(CommonUtil.hash(user.getPassword()));
				userManager.update(old);
				session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
				return new ModelAndView("redirect:/user");
			}
			
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息："+e.getMessage());
			e.printStackTrace();
			
		}
		return new ModelAndView("/user/editpw","user",user);
	}
	
	/**
	 * 修改用户的角色
	 * @param id
	 * @param user
	 * @param password1
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/editroles")
	@RequiresPermissions("user:menu")
	public ModelAndView editroles(Integer id,User user,String[] roleIds, HttpServletRequest request) {
		if("get".equalsIgnoreCase(request.getMethod())){	
			user =userManager.findById(id);
			List<UserRole> userRoles = roleManager.findUserRoles(user);
			List<Role> roles =roleManager.findAll();			
			List<UserRoleVo> userRoleVos = convert(roles, userRoles);		
			ModelMap model =new ModelMap();
			model.addAttribute("user", user);
			model.addAttribute("userRoleVos",userRoleVos);
			return  new ModelAndView("/user/editroles",model);
		}
		roleManager.updateUserRoles(convertIds(roleIds), id);
		
		return new ModelAndView("redirect:/user");
	}
	private Set<Integer> convertIds(String[] ids){
		
		Set<Integer> set =new TreeSet<Integer>();
		if(null==ids){
			return set;
		}
		for(String id:ids){
			set.add(Integer.parseInt(id));
		}
		return set;
		
	}

	@RequestMapping("/user/del")
	@RequiresPermissions("user:menu")
	public void del(Integer id, HttpServletResponse response)  throws IOException {
		response.setContentType("text/json");
		try {
			userManager.deleteById(id);
			response.getWriter().write("success");
			return;
		} catch (Exception e) {
			String message =getCause(e).getMessage();
			response.getWriter().write(message);
		}
		
		
	}
	

	List<UserRoleVo> convert(List<Role> roles,List<UserRole> userRoles){
		List<UserRoleVo> rt = new ArrayList<UserRoleVo>();
		for(Role role:roles){
			boolean flag =false;
			for(UserRole userrole:userRoles){
				if(role.getId().equals(userrole.getRole().getId())){
					flag =true;
					break;
				}
			}
			rt.add(new UserRoleVo(role,flag));
		}
		return rt;
	}
	
	

	
}
