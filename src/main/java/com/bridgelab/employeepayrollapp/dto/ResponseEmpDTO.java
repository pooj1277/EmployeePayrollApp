package com.bridgelab.employeepayrollapp.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResponseEmpDTO {

	private int id;
	private String name;
	private String gender;
	private List<String> department;
	private double salary;
	private String startDate;
	private String notes;
	private String profilePic;
}
