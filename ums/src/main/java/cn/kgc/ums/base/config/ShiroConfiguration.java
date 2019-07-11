package cn.kgc.ums.base.config;
//注释
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.kgc.ums.util.ShiroDBRealm;

@Configuration
public class ShiroConfiguration {
	/**
	 * 将ShiroDBRealm变成spring的一个bean
	 * @return
	 */
	@Bean
	public ShiroDBRealm shiroDBRealm() {
		return new ShiroDBRealm();
	}
	/**
	 * 创建SecurityManager所对应的bean
	 * @return
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroDBRealm());
		return securityManager;
	}
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		
		shiroFilter.setSecurityManager(securityManager);
		
		shiroFilter.setLoginUrl("/user/login");
		
		shiroFilter.setSuccessUrl("/user/list");
		
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/user/registry", "anon");
		filterChainDefinitionMap.put("/user/logout", "logout");
		filterChainDefinitionMap.put("/**", "authc");
		
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilter;
	}
}
