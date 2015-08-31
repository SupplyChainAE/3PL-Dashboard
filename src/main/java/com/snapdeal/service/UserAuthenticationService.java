package com.snapdeal.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService implements UserDetailsService {

	@Inject
	@Named("userService")
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String userName)
	throws UsernameNotFoundException {
		com.snapdeal.entity.User myUser = userService.getUserByUserName(userName);
		/*List<GrantedAuthority> authorities = getAuthorities(myUser.getUserRoles());
		return new User(myUser.getUserName(), 
				myUser.getPassword(), myUser.isEnabled(), 
				true, true, true, authorities);*/
		if(myUser != null)
		{
			return myUser;	
		}else {
			return new com.snapdeal.entity.User();
		}

	}

}
