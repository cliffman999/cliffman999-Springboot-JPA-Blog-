package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

/*스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails타입의 오브젝트를
  스프링 시큐리티의 고유한 세션저장장소에 저장을 한다.*/
@SuppressWarnings("serial")
public class PrincipalDetail implements UserDetails{
	
	private User user; //콤포지션(객체를 품고있는것)
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	//계정이 만료되지 않았는지를 리턴한다.(true:만료안됨)
	@Override
	public boolean isAccountNonExpired() { 
		return true;
	}
	
	//계정이 잠겨있지 않았는지를 리턴한다.(true:잠기지않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	//비밀번호가 만료되지 않았는지 리턴한다.(true:만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	//계정이 활성화(사용가능)인지 리턴한다.(true:활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	//계정이 가지고 있는 권한목록을 리턴한다.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->{return "ROLE_"+user.getRole();});
		
		return collectors;
	}
	
	

}