package kr.co.cesco.econtract.web.test.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("Customer")
@Getter
@Setter
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer customerId;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String street;
	private String city;
	private String state;
	private String zipCode;
}

