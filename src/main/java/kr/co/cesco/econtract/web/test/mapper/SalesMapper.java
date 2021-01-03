package kr.co.cesco.econtract.web.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import kr.co.cesco.econtract.web.test.domain.BrandReq.CreateDto;
import kr.co.cesco.econtract.web.test.domain.Customer;
import kr.co.cesco.econtract.web.test.domain.CustomerReq;
import kr.co.cesco.econtract.web.test.domain.CustomerRes;
import kr.co.cesco.econtract.web.test.dto.PeopleDto;
import kr.co.cesco.econtract.web.test.dto.Person;
import kr.co.cesco.econtract.web.test.dto.StateDto;

@Mapper
public interface SalesMapper {
	
	// Select ===================================================================

	List<Customer> findCustomers();

	// call procedure
	List<Customer> findCustomers2();

	// return hashmap
	List<Map<String, Object>> findCustomers3();

	List<Customer> findCustomersByState(@Param("state") String state);

	@Select(value = "{CALL getCustomersByState(#{state, mode=IN, javaType=String})}")
	@Options(statementType = StatementType.CALLABLE)
	List<Customer> getCustomersByState2(@Param("state") String state);
	
	@Select(value = "{CALL getCustomersByState(#{state, mode=IN, javaType=String})}")
	@Options(statementType = StatementType.CALLABLE)
	List<Customer> getCustomersByState3(StateDto state);
	
	@Select(value = "{CALL getCustomersByState(#{state, mode=IN, javaType=String})}")
	@Options(statementType = StatementType.CALLABLE)
	List<CustomerRes.CustomerDto> getCustomersByState4(CustomerReq.SearchDto searchDto);
	
	
	void addPerson(Person person);
	
	void addPerson2(PeopleDto.PersonDto person);
	
	// out parameter는 반드시 jdbcType이 필요
	@Insert(value = "{CALL test.addPerson(#{name, mode=IN, javaType=String}, "
			+ "#{age, mode=IN, javaType=Integer}, "
			+ "#{id, mode=OUT, javaType=Integer, jdbcType=INTEGER})}")
	@Options(statementType = StatementType.CALLABLE)
	void addPerson3(PeopleDto.PersonDto person);
	 
	// 조건 파라미터는 mapper(repository)에서만 명시해도 쿼리는 실행됨
	Person getPerson(@Param("id") Integer id);
	
	
	// Insert ===================================================================

	@Insert(value = "{CALL production.insertNewBrand(#{brandName, mode=IN, javaType=String})}")
	@Options(statementType = StatementType.CALLABLE)
	int insertBrand(CreateDto createDto);






}
