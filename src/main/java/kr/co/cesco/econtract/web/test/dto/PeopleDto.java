package kr.co.cesco.econtract.web.test.dto;

import java.io.Serializable;

import lombok.Data;

public class PeopleDto {
	
	@Data
	public static class PersonDto implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer id;
		private String name;
		private Integer age;
	}

}
