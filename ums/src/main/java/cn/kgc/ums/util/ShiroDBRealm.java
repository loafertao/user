package cn.kgc.ums.util;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.kgc.ums.pojo.entity.User;
import cn.kgc.ums.service.UserService;

public class ShiroDBRealm extends AuthorizingRealm {
	@Resource(name = "userService")
	private UserService userService;
	@Autowired
	private HttpSession session;

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		return null;
	}

	//shiro进行认证的时候，调用的方法
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		String loginName = userToken.getUsername();
		
		try {
			User user = userService.getUserByLoginName(loginName);
			if (user != null) {
				SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getLoginName(),user.getPassword(),getName());
				session.setAttribute("user", user);
				return info;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
