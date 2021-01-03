package kr.co.cesco.econtract.config.security.userdetails;

import kr.co.cesco.econtract.web.users.model.User;
import kr.co.cesco.econtract.web.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserService userService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		User user = userService.findUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with userId: " + username));
		return CustomUserDetails.build(user);
	}

}
