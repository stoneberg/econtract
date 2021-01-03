package kr.co.cesco.econtract.web.users.service;

import kr.co.cesco.econtract.web.users.mapper.UserMapper;
import kr.co.cesco.econtract.web.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;

    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(userMapper.findUserByUsername(username));
    }

}
