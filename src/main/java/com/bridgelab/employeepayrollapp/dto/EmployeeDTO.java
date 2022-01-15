package com.bridgelab.employeepayrollapp.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class EmployeeDTO {

	//@Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2}$", message = "Name is not valid.")
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String gender;
	
	private List<String> department;
	
	private String profilePic;
	
	private String notes;

	@Min(value = 500, message = "Salary must be greater than 500.")
	private double salary;

//	@JsonDeserialize(using = LocalDateDeserializer.class)
//	@JsonSerialize(using = LocalDateSerializer.class)
	private String	 startDate;
	
	//this.startDate = LocalDate.parse(String);
	
}
