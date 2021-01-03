package kr.co.cesco.econtract.web.test.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

import lombok.Getter;

@Alias("Employee")
@Getter
public class Employee implements Serializable {
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
