package cn.kgc.ums.service;

import cn.kgc.ums.pojo.entity.User;
import cn.kgc.ums.pojo.vo.SystemPage;

public interface UserService {

	User getUserByLoginName(String loginName) throws Exception;

	boolean saveUser(User user)throws Exception;

	SystemPage<User> getUserListByPage(SystemPage<User> systemPage) throws Exception ;

	User getUserById(Long userId);

}
