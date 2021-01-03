package kr.co.cesco.econtract.web.users.mapper;

import kr.co.cesco.econtract.web.users.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User findUserByUsername(@Param("username") String username);
}
