package kr.co.cesco.econtract.common.enums;

import kr.co.cesco.econtract.common.mybatis.typehandler.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UserType implements CodeEnum {
    SA("SA"), // ADMIN
    SD("SD"),
    SC("SC");

    @Getter
    private final String code;
}