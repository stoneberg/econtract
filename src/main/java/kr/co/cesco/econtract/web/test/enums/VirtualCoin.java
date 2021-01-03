package kr.co.cesco.econtract.web.test.enums;

import kr.co.cesco.econtract.common.mybatis.typehandler.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum VirtualCoin implements CodeEnum {
    BIT_COIN("BTC"),
    ETHEREUM("ETH"),
    LITE_COIN("LTC"),
    RIPPLE("XRP");

    @Getter
    private final String code;
}
