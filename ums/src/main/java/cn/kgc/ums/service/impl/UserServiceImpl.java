package cn.kgc.ums.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.kgc.ums.dao.UserDao;
import cn.kgc.ums.pojo.entity.User;
import cn.kgc.ums.pojo.vo.SystemPage;
import cn.kgc.ums.service.UserService;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	@Resource(name = "userDao")
	private UserDao userDao;

	
	public User getUserByLoginName(String loginName) throws Exception {
		List<User> userList = userDao.findAll(new Specification<User>() {
			private static final long serialVersionUID = 1L;
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.equal(root.get("loginName"), loginName);
				return query.where(predicate).getRestriction();
			}
		});
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}


	@Override
	public boolean saveUser(User user) throws Exception {
		if (userDao.save(user) != null) {
			return true;
		}
		return false;
	}


	public SystemPage<User> getUserListByPage(SystemPage<User> systemPage) throws Exception {
		Pageable pageable = PageRequest.of(systemPage.getPageNum(), systemPage.getPageSize(), new Sort(Direction.ASC, "createDate"));
		Page<User> page = userDao.findAll(pageable);
		systemPage.parseToSystemPage(page);
		return systemPage;
	}


	
	public User getUserById(Long userId) {
		return userDao.getOne(userId);
	}

}
