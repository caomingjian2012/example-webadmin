package com.palmtech.ad.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.common.plugins.spring.mvc.Constants;
import com.palmtech.ad.bean.RolePermissionVo;
import com.palmtech.ad.entity.Permission;
import com.palmtech.ad.entity.Role;
import com.palmtech.ad.entity.RolePermission;
import com.palmtech.ad.manager.RoleManager;
/**
 * 后台框架页面
 * @author Administrator
 *
 */
@Controller
public class RoleController {
	@Autowired
	private RoleManager roleManager;
	
	
	@RequestMapping("/role")
	@RequiresRoles("admin")
	public ModelAndView query() {
		
		
		List<Role> list = roleManager.findAll();
		
		
		return new ModelAndView("/role/list", "roles", list) ;
	}
	
	
	@RequestMapping("/role/add")
	@RequiresRoles("admin")
	public ModelAndView add(Role role, HttpServletRequest request,HttpSession session) {
		if("get".equalsIgnoreCase(request.getMethod())){			
			return new ModelAndView("/role/add");
		}
		try{
			Date now =new Date();
			role.setCreateTime(now);
			role.setLasteditTime(now );
			roleManager.save(role);
			session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
			return new ModelAndView("redirect:/role");
		}catch(DataIntegrityViolationException ce){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息：对象已经存在");
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息："+e.getMessage());
			e.printStackTrace();
			
		}
		return new ModelAndView("/role/add");
		
	}
	
	
	@RequestMapping("/role/edit")
	@RequiresRoles("admin")
	public ModelAndView edit(Integer id,Role role, HttpServletRequest request,HttpSession session) {
		if("get".equalsIgnoreCase(request.getMethod())){	
			role =roleManager.findById(id);
			if(null==role){
				session.setAttribute(Constants.ERR_MESSAGE,"对象不存在role:"+id);
				
			}
			return  new ModelAndView("/role/edit","role",role);
		}
		try{
			Role old =roleManager.findById(role.getId());
			old.setName(role.getName());
			old.setContent(role.getContent());		
			roleManager.update(old);
			session.setAttribute(Constants.SUC_MESSAGE, "操作成功");
			return new ModelAndView("redirect:/role");
		}catch(DataIntegrityViolationException ce){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息：对象已经存在");
		}catch(Exception e){
			session.setAttribute(Constants.ERR_MESSAGE,"操作失败，失败信息："+e.getMessage());
			e.printStackTrace();
			
			
		}
		return new ModelAndView("/role/edit","role",role);
		
	}
	/**
	 * 修改角色的权限
	 * @param id
	 * @param user
	 * @param password1
	 * @param roleIds
	 * @param request
	 * @return
	 */
	@RequestMapping("/role/editperms")
	@RequiresRoles("admin")
	public ModelAndView editperms(Integer id,Role role,String[] permissionIds, HttpServletRequest request) {
		if("get".equalsIgnoreCase(request.getMethod())){	
			role =roleManager.findById(id);
			List<RolePermission> rolePermissions = roleManager.findPermissionsByRole(role);
			List<Permission> permissions =roleManager.findAllPermission();	
			List<RolePermissionVo> rolePermissionVos = convert(permissions, rolePermissions);		
			ModelMap model =new ModelMap();
			model.addAttribute("role", role);
			model.addAttribute("rolePermissionVos",rolePermissionVos);
			return  new ModelAndView("/role/editperms",model);
		}
		roleManager.updateRolePermissions(convertIds(permissionIds), id);
		
		
		return new ModelAndView("redirect:/role");
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
	List<RolePermissionVo> convert(List<Permission> permissions,List<RolePermission> rolePermissions){
		List<RolePermissionVo> rt = new ArrayList<RolePermissionVo>();
		for(Permission permission:permissions){
			boolean flag =false;
			for(RolePermission rolePermission : rolePermissions){
				if(permission.getId().equals(rolePermission.getPermission().getId())){
					flag =true;
					break;
				}
			}
			rt.add(new RolePermissionVo(permission,flag));
		}
		return rt;
	}
	@RequestMapping("/role/del")
	@RequiresRoles("admin")
	public void del(Integer id, HttpServletResponse response)  throws IOException {
		response.setContentType("text/json");
		try {
			roleManager.deleteById(id);
			response.getWriter().write("success");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/json");

		response.getWriter().write("fail");
	}
	

	
	
}
