package kr.co.cesco.econtract.common.enums;

import kr.co.cesco.econtract.common.mybatis.typehandler.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum RoleType implements CodeEnum {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    @Getter
    private final String code;
}
