package com.bridgelab.employeepayrollapp.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String gender;
	private double salary;
	private LocalDate startDate;
	private String notes;
	private String profilePic;
	@ElementCollection
	@CollectionTable(name = "employee_department", joinColumns = @JoinColumn(name="id"))
	@Column(name = "department")
	private List<String> department;
	
}
