package cn.kgc.ums.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kgc.ums.base.controller.BaseController;
import cn.kgc.ums.pojo.entity.User;
import cn.kgc.ums.pojo.vo.SystemPage;
import cn.kgc.ums.service.UserService;
@Controller("userController")
@RequestMapping("/user")
public class UserController extends BaseController{
	@Resource(name = "userService")
	private UserService userService;
	
	/**
	 * 加载登录页面
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String getLoginForm()throws Exception {
		return "user/user_login";
	}
	
	/**
	 *  用户登录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String loginUser() throws Exception {
		return "redirect:logout";
	}

	/**
	 * 加载注册页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registry",method = RequestMethod.GET)
	public String getRegistryForm()throws Exception {
		return "user/user_registry";
	}
	
	/**
	 * 用户进行注册
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registry",method = RequestMethod.POST)
	public String registryUser(User user) throws Exception{
		if (userService.saveUser(user)) {
			return "redirect:login";
		}
		return "redirect:registry";
	}
	
	/**
	 * 分页查询信息列表
	 * @param pageNum  当前页数
	 * @param pageSize 每页显示条数
	 * @param map 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String getUserListPage(Integer pageNum,Integer pageSize,ModelMap map)throws Exception{
		SystemPage<User> systemPage = new SystemPage<User>(pageNum, pageSize);
		systemPage  = userService.getUserListByPage(systemPage);
		map.put("page", systemPage);
 		return "user/user_list";
	}
	/**
	 * 通过用户所携带的主键查找所对应的用户信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/{userId}")
	public String updateUserForm(@PathVariable("userId")Long userId,ModelMap map)throws Exception{
		User user = userService.getUserById(userId);
		map.put("user", user);
		return "user/user_update";
	}
	@RequestMapping(value = "/update")
	public String updateUser(User user) throws Exception{
		User cekerUser = userService.getUserByLoginName(user.getLoginName());
		if (cekerUser == null || cekerUser != null && cekerUser.getUserId().equals(user.getUserId())) {
			
		}
		return "user/user_update";
	}
}
