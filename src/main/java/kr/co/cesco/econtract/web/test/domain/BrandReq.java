package kr.co.cesco.econtract.web.test.domain;

import java.io.Serializable;

import lombok.Data;

public class BrandReq {
	
	@Data
	public static class CreateDto implements Serializable {
		private static final long serialVersionUID = 1L;
		private String brandName;
		private Integer brandId;
	}

}
