package com.bridgelab.employeepayrollapp.convertor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bridgelab.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelab.employeepayrollapp.dto.ResponseEmpDTO;
import com.bridgelab.employeepayrollapp.model.Employee;

@Component
public class EntityToDTOConvertor {

	public Employee dtoToEntity(EmployeeDTO dto) {
		String startDate = dto.getStartDate();
		LocalDate date = LocalDate.parse(startDate);
		Employee emp = new Employee();
		emp.setDepartment(dto.getDepartment());
		emp.setGender(dto.getGender());
		emp.setName(dto.getName());
		emp.setNotes(dto.getNotes());
		emp.setProfilePic(dto.getProfilePic());
		emp.setSalary(dto.getSalary());
		emp.setStartDate(date);
		return emp;
//		ModelMapper mapper = new ModelMapper();
//		Employee employee = mapper.map(dto, Employee.class);
//		return employee;
	}

	public ResponseEmpDTO entityToResponseDTO(Employee emp) {
		
		ResponseEmpDTO responseDto = new ResponseEmpDTO();
		responseDto.setDepartment(emp.getDepartment());
		responseDto.setGender(emp.getGender());
		responseDto.setId(emp.getId());
		responseDto.setName(emp.getName());
		responseDto.setNotes(emp.getNotes());
		responseDto.setProfilePic(emp.getProfilePic());
		responseDto.setSalary(emp.getSalary());
		responseDto.setStartDate(emp.getStartDate().toString());
		return responseDto;
		
//		ModelMapper mapper = new ModelMapper();
//		ResponseEmpDTO responseDto = mapper.map(emp, ResponseEmpDTO.class);
//		return responseDto;
	}

	public List<Employee> dtoListToEntityList(List<EmployeeDTO> dto) {
		return dto.stream().map(dtoObject -> dtoToEntity(dtoObject)).collect(Collectors.toList());
	}

	public List<ResponseEmpDTO> entityListToDTOList(List<Employee> empList) {
		return empList.stream().map(empObject -> entityToResponseDTO(empObject)).collect(Collectors.toList());
	}
}
