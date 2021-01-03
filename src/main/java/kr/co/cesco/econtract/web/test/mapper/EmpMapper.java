package kr.co.cesco.econtract.web.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.cesco.econtract.web.test.domain.Employee;
import kr.co.cesco.econtract.web.test.domain.EmployeeReq;
import kr.co.cesco.econtract.web.test.domain.EmployeeRes;

@Mapper
public interface EmpMapper {
	
	List<Employee> findEmps();

	List<EmployeeRes.ResultDto> findEmployees(EmployeeReq.SearchDto searchDto);

}
