package kr.co.cesco.econtract.web.test.domain;

import java.io.Serializable;

import lombok.Data;

public class CustomerRes {
	
	@Data
	public static class CustomerDto implements Serializable {
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

}
