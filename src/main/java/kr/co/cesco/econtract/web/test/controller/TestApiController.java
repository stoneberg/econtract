package kr.co.cesco.econtract.web.test.controller;

import java.util.List;

import kr.co.cesco.econtract.web.test.dto.*;
import kr.co.cesco.econtract.web.test.dto.UserVirtualCoinDto.InvestorReq;
import kr.co.cesco.econtract.web.test.dto.UserVirtualCoinDto.InvestorRes;
import kr.co.cesco.econtract.web.test.dto.UserVirtualCoinDto.UserVirtualCoinReq;
import kr.co.cesco.econtract.web.test.dto.UserVirtualCoinDto.UserVirtualCoinRes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.cesco.econtract.web.test.domain.BrandReq;
import kr.co.cesco.econtract.web.test.domain.Customer;
import kr.co.cesco.econtract.web.test.domain.CustomerReq;
import kr.co.cesco.econtract.web.test.domain.Employee;
import kr.co.cesco.econtract.web.test.domain.EmployeeReq;
import kr.co.cesco.econtract.web.test.domain.EmployeeRes.ResultDto;
import kr.co.cesco.econtract.web.test.domain.Product;
import kr.co.cesco.econtract.web.test.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/test")
@RestController
public class TestApiController {
	
	private final TestService testService;

	// Error Test ==============================================================
    @GetMapping("error/{id}")
    public List<Employee> getError(@PathVariable("id") String id) {
        log.info("getError=======================>{}", id);
        return testService.causeTestError();
    }

    @GetMapping("error2/{id}")
    public List<Employee> getError2(@PathVariable("id") Integer id) {
        log.info("getError2=======================>{}", id);
        return testService.causeTestError2();
    }

	// Select ===================================================================
	
    @GetMapping("employees")
    public List<Employee> getEmps() {
    	return testService.findEmps();
    }
    
    @PostMapping("employees2")
    public List<ResultDto> getEmployees(@RequestBody EmployeeReq.SearchDto searchDto) {
    	return testService.getEmployees(searchDto);
    }
    
    @GetMapping("customers")
    public List<Customer> getCustomers() {
    	log.info("@customers===================>");
    	return testService.findCustomers();
    }
    
    // call procedure
    @GetMapping("customers2")
    public List<Customer> getCustomers2() {
    	log.info("@customers2===================>");
    	return testService.findCustomers2();
    }
    
    // result to hashmap
    @GetMapping("customers3")
    public List<?> getCustomers3() {
    	log.info("@customers3===================>");
    	return testService.findCustomers3();
    }
    
	// return hashmap to pojo
    @GetMapping("customers4")
    public List<?> getCustomers4() {
    	log.info("@customers4===================>");
    	return testService.findCustomers4();
    }
    
    @GetMapping("customers/state/{state}")
    public List<?> getCustomersByState(@PathVariable("state") String state) {
    	log.info("@getCustomersByState===================>");
    	return testService.getCustomersByState(state);
    }
    
    @GetMapping("customers2/state/{state}")
    public List<?> getCustomersByState2(@PathVariable("state") String state) {
    	log.info("@getCustomersByState2===================>");
    	return testService.getCustomersByState2(state);
    }
    
    @PostMapping("customers3/state")
    public List<?> getCustomersByState3(@RequestBody StateDto state) {
    	log.info("@getCustomersByState3===================>");
    	return testService.getCustomersByState3(state);
    }
    
    @PostMapping("customers4/state")
    public List<?> getCustomersByState4(@RequestBody CustomerReq.SearchDto searchDto) {
    	log.info("@getCustomersByState4===================>");
    	return testService.getCustomersByState4(searchDto);
    }
    
    
    @PostMapping("person")
    public Integer addPerson(@RequestBody Person person) {
    	log.info("@addPerson===================>{}", person);
    	 testService.addPerson(person);
    	 log.info("@@addPerson=================>{}", person.getId());
    	 return person.getId();
    }
    
    @PostMapping("person2")
    public Integer addPerson2(@RequestBody PeopleDto.PersonDto person) {
    	log.info("@addPerson2===================>{}", person);
    	 testService.addPerson2(person);
    	 log.info("@@addPerson2=================>{}", person.getId());
    	 return person.getId();
    }
    
    @PostMapping("person3")
    public Integer addPerson3(@RequestBody PeopleDto.PersonDto person) {
    	log.info("@addPerson3===================>{}", person);
    	 testService.addPerson3(person);
    	 log.info("@@addPerson3=================>{}", person.getId());
    	 return person.getId();
    }
    
    
    @GetMapping("person/{id}")
    public Person getPerson(@PathVariable Integer id) {
    	log.info("@getPerson===================>");
    	return testService.getPerson(id);
    }
    
    
    // Insert =============================================================================
    
    @PostMapping("brands")
    public int createBrand(@RequestBody BrandReq.CreateDto createDto) {
    	log.info("@createBrand===================>");
    	return testService.createBrand(createDto);
    }
    
    
	// Association  ===================================================================
    
    @GetMapping("collection/products")
    public List<ProductDto> getProducts() {
    	return testService.findProducts();
    }
    
    @GetMapping("collection/products/{productId}")
    public List<ProductDto> getProductsById(final @PathVariable("productId") Integer productId) {
    	return testService.findProductsById(productId);
    }
    
    @GetMapping("collection/products2/{productId}")
    public List<ProductDto> getProductsById2(final @PathVariable("productId") Integer productId) {
    	return testService.findProductsById2(productId);
    }
    
    @GetMapping("collection/products3/{productId}")
    public List<Product.ProductDto2> getProductsById3(final @PathVariable("productId") Integer productId) {
    	return testService.findProductsById3(productId);
    }
    
    @GetMapping("collection/products4/{productId}")
    public List<Product.ProductDto2> getProductsById4(final @PathVariable("productId") Integer productId) {
    	return testService.findProductsById4(productId);
    }


    // Enum Typehandler  ===================================================================
    @GetMapping("enums/user-virtual-coins")
    public List<UserVirtualCoinRes> getUserVirtualCoins() {
        return testService.findUserVirtualCoins();
    }

    @PostMapping("enums/user-virtual-coins")
    public int addUserVirtualCoin(@RequestBody UserVirtualCoinReq userVirtualCoinReq) {
        return testService.insertUserVirtualCoin(userVirtualCoinReq);
    }

    @GetMapping("enums/investors")
    public List<InvestorRes> getInvestors() {
        return testService.findInvestors();
    }

    @PostMapping("enums/investors")
    public int addInvestor(@RequestBody InvestorReq investorReq) {
        return testService.insertInvestor(investorReq);
    }
    
}