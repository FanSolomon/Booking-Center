package com.fansolomon.bookingService.authentication;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fansolomon.bookingService.entity.BcUser;
import com.fansolomon.bookingService.service.IBcUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Colin
 * @since 2020-07-12
 */
@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private IBcUserService bcUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("------登录用户名：{}", username);
		//根据用户名查找用户信息
		//这里用了spring security中的用户对象，也可以用自己项目中实际的用户对象(需要实现UserDetails接口)
		BcUser bcUser = bcUserService.getOne(new QueryWrapper<BcUser>().lambda()
			.eq(BcUser::getUsername, username));
		if (bcUser == null) {
			log.info("------用户:{}不存在", username);
			throw new UsernameNotFoundException("用户不存在");
		}
		log.info("------数据库密码：{}", bcUser.getPassword());
		return new User(username, bcUser.getPassword(),
				true, true, true, true,
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
