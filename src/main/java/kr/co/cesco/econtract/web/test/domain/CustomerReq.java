package kr.co.cesco.econtract.web.test.domain;

import java.io.Serializable;

import lombok.Data;

public class CustomerReq {
	
	@Data
	public static class SearchDto implements Serializable {
		private static final long serialVersionUID = 1L;
		private String state;
		private String city;
	}
}
