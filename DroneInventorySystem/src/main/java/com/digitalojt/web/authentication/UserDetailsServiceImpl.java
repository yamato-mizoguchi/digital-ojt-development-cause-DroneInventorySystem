package com.digitalojt.web.authentication;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.digitalojt.web.entity.AdminInfo;
import com.digitalojt.web.repository.AdminInfoRepository;

import lombok.RequiredArgsConstructor;


/**
 * UserDetailsServiceImpl
 *
 * @author yamato mizoguchi
 * 
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

	private final AdminInfoRepository adminInfoRepository;
	
	/**							
	 * ユーザー情報生成							
	 * 							
	 * @param ログインID							
	 * @throws UsernameNotFoundException							
	 */	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AdminInfo adminInfo = adminInfoRepository.findByAdminId(username).orElseThrow(() -> new UsernameNotFoundException(username));
		
		return User.withUsername(adminInfo.getAdminId())
				.password(adminInfo.getPassword())
				.roles("ADMIN")
				.build();
	}
}
