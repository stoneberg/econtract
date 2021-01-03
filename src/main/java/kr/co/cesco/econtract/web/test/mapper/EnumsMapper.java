package kr.co.cesco.econtract.web.test.mapper;

import kr.co.cesco.econtract.web.test.dto.UserVirtualCoinDto;
import kr.co.cesco.econtract.web.test.dto.UserVirtualCoinDto.InvestorReq;
import kr.co.cesco.econtract.web.test.dto.UserVirtualCoinDto.InvestorRes;
import kr.co.cesco.econtract.web.test.dto.UserVirtualCoinDto.UserVirtualCoinReq;
import kr.co.cesco.econtract.web.test.dto.UserVirtualCoinDto.UserVirtualCoinRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnumsMapper {
    List<UserVirtualCoinRes> findUserVirtualCoins();
    int insertUserVirtualCoin(UserVirtualCoinReq userVirtualCoinReq);
    List<InvestorRes> findInvestors();
    int insertInvestor(InvestorReq investorReq);
}
