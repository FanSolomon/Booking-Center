package com.fansolomon.bookingService.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Colin
 * @since 2020-07-12
 */
@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("------登录用户名：{}", username);
		//根据用户名查找用户信息
		//这里用了spring security中的用户对象，也可以用自己项目中实际的用户对象(需要实现UserDetails接口)
		String encodePassword = passwordEncoder.encode("123456");
		// TODO 待改为查库并进行比对
		log.info("------加密后的密码：{}", encodePassword);
		return new User(username, encodePassword,
				true, true, true, true,
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
