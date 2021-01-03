package kr.co.cesco.econtract.web.test.domain;

import java.io.Serializable;

import lombok.Data;

public class BrandRes {
	
	@Data
	public static class ResultDto implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer brandId;
	}

}
