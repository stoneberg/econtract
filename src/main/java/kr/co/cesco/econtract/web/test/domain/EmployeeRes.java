package kr.co.cesco.econtract.web.test.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

public class EmployeeRes {
	
	@Data
	public static class ResultDto implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer empNo;
		private String ename;
		private String job;
		private Integer mgr;
		private LocalDateTime hireDate;
		private Float sal;
		private Float comm;
		private Integer deptNo;
	}

}
