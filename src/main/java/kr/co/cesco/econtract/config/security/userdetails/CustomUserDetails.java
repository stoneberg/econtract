package kr.co.cesco.econtract.config.security.userdetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.cesco.econtract.common.enums.RoleType;
import kr.co.cesco.econtract.common.enums.UserType;
import kr.co.cesco.econtract.web.users.model.User;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ToString
@EqualsAndHashCode
public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final String username; // userId

    private final String fullname;

    private final String deptname;

    private final String position;

    private final String userType;

    private final String email;


    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String username, String fullname, String deptname, String position, String userType,
                             String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.fullname = fullname;
        this.deptname = deptname;
        this.position = position;
        this.userType = userType;
        this.email = email;
        this.password = password;
        this.authorities = authorities; // user roles(ADMIN, USER)
    }

    public static CustomUserDetails build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (StringUtils.equals(user.getUserType(), UserType.SA.name())) {
            authorities.add(new SimpleGrantedAuthority(RoleType.ROLE_ADMIN.name()));
        }
        authorities.add(new SimpleGrantedAuthority(RoleType.ROLE_USER.name()));

        return new CustomUserDetails(
                user.getUsername(),
                user.getFullname(),
                user.getDeptname(),
                user.getPosition(),
                user.getUserType(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    public String getFullname() {
        return fullname;
    }
    public String getPosition() {
        return position;
    }
    public String getDeptname() {
        return deptname;
    }
    public String getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
