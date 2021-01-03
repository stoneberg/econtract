package kr.co.cesco.econtract.web.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.co.cesco.econtract.common.exception.TestAppException;
import kr.co.cesco.econtract.common.exception.type.TestAppExceptionType;
import kr.co.cesco.econtract.web.test.dto.*;
import kr.co.cesco.econtract.web.test.dto.UserVirtualCoinDto.InvestorReq;
import kr.co.cesco.econtract.web.test.dto.UserVirtualCoinDto.InvestorRes;
import kr.co.cesco.econtract.web.test.dto.UserVirtualCoinDto.UserVirtualCoinReq;
import kr.co.cesco.econtract.web.test.dto.UserVirtualCoinDto.UserVirtualCoinRes;
import kr.co.cesco.econtract.web.test.mapper.EnumsMapper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.cesco.econtract.web.test.domain.BrandReq;
import kr.co.cesco.econtract.web.test.domain.Customer;
import kr.co.cesco.econtract.web.test.domain.CustomerReq;
import kr.co.cesco.econtract.web.test.domain.CustomerRes;
import kr.co.cesco.econtract.web.test.domain.Employee;
import kr.co.cesco.econtract.web.test.domain.EmployeeReq;
import kr.co.cesco.econtract.web.test.domain.EmployeeRes.ResultDto;
import kr.co.cesco.econtract.web.test.domain.Product;
import kr.co.cesco.econtract.web.test.mapper.EmpMapper;
import kr.co.cesco.econtract.web.test.mapper.ProductMapper;
import kr.co.cesco.econtract.web.test.mapper.SalesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {
	
	private final EmpMapper empRepository;
	private final SalesMapper salesRepository;
	private final ProductMapper productMapper;
	private final EnumsMapper enumsMapper;
	private final ObjectMapper objectMapper;
	
	// Error =============================================================
	
	public List<Employee> causeTestError() {
		if (true) {
			log.error("@causeTestError=====================>");
			throw new TestAppException(TestAppExceptionType.CAUSE_TEST_ERROR);
		}
		return empRepository.findEmps();
	}

	public List<Employee> causeTestError2() {
		if (true) {
			log.error("@causeTestError2=====================>");
			throw new TestAppException(TestAppExceptionType.CAUSE_TEST2_ERROR);
		}
		return empRepository.findEmps();
	}
	
	// Select =============================================================
	
	public List<Employee> findEmps() {
		return empRepository.findEmps();
	}
	
	public List<ResultDto> getEmployees(EmployeeReq.SearchDto searchDto) {
		return empRepository.findEmployees(searchDto);
	}
	
	public List<Customer> findCustomers() {
		return salesRepository.findCustomers();
	}
	
	
	public List<Customer> findCustomers2() {
		return salesRepository.findCustomers2();
	}
	
	public List<Map<String, Object>> findCustomers3() {
		return salesRepository.findCustomers3();
	}

	public List<Customer> findCustomers4() {
		
		List<Customer> customers = new ArrayList<>();
		List<?> customerMaps = salesRepository.findCustomers3(); // from hashmap
		
		customerMaps.forEach(map -> {
			Customer customer = objectMapper.convertValue(map, Customer.class);
			customers.add(customer);
		});
		
		return customers; // to pojo
	}
	
	public List<Customer> getCustomersByState(String state) {
		return salesRepository.findCustomersByState(state);
	}
	
	public List<Customer> getCustomersByState2(String state) {
		return salesRepository.getCustomersByState2(state);
	}
	
	public List<Customer> getCustomersByState3(StateDto state) {
		return salesRepository.getCustomersByState3(state);
	}
	
	public List<CustomerRes.CustomerDto> getCustomersByState4(CustomerReq.SearchDto searchDto) {
		return salesRepository.getCustomersByState4(searchDto);
	}
	
	
	public void addPerson(Person person) {
		salesRepository.addPerson(person);;
	}
	
	
	public void addPerson2(PeopleDto.PersonDto person) {
		salesRepository.addPerson2(person);;
	}
	
	public void addPerson3(PeopleDto.PersonDto person) {
		salesRepository.addPerson3(person);;
	}
	
	public Person getPerson(Integer id) {
		log.info("@getPerson===================>{}", id);
		return salesRepository.getPerson(id);
	}

	// Insert =============================================================
	
	public int createBrand(BrandReq.CreateDto createDto) {
		return salesRepository.insertBrand(createDto);
	}

	// Collection =============================================================
	public List<ProductDto> findProducts() {
		return productMapper.findProducts();
	}

	public List<ProductDto> findProductsById(Integer productId) {
		return productMapper.findProductsById(productId);
	}
	
	public List<ProductDto> findProductsById2(Integer productId) {
		return productMapper.findProductsById2(productId);
	}
	
	public List<Product.ProductDto2> findProductsById3(Integer productId) {
		return productMapper.findProductsById3(productId);
	}
	
	public List<Product.ProductDto2> findProductsById4(Integer productId) {
		return productMapper.findProductsById4(productId);
	}


	// Enum Typehandler =============================================================
	public List<UserVirtualCoinRes> findUserVirtualCoins() {
		return enumsMapper.findUserVirtualCoins();
	}

	public int insertUserVirtualCoin(UserVirtualCoinReq userVirtualCoinReq) {
		return enumsMapper.insertUserVirtualCoin(userVirtualCoinReq);
	}

	public List<InvestorRes> findInvestors() {
		return enumsMapper.findInvestors();
	}

	public int insertInvestor(InvestorReq investorReq) {
		return enumsMapper.insertInvestor(investorReq);
	}
}
