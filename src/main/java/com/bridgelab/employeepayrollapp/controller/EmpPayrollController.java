package com.bridgelab.employeepayrollapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelab.employeepayrollapp.convertor.EntityToDTOConvertor;
import com.bridgelab.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelab.employeepayrollapp.dto.ResponseEmpDTO;
import com.bridgelab.employeepayrollapp.model.Employee;
import com.bridgelab.employeepayrollapp.services.IEmployeeService;

@RestController
@RequestMapping("/emppayroll")
@CrossOrigin(originPatterns = "*")
public class EmpPayrollController {

	@Autowired
	private IEmployeeService empService;

	@Autowired
	private EntityToDTOConvertor convertor;

	@GetMapping(value = { "", "/", "/get" })
	public ResponseEntity<List<ResponseEmpDTO>> getAll() {

		List<Employee> empList = empService.getAllEmp();
		List<ResponseEmpDTO> responseEmpList = convertor.entityListToDTOList(empList);
		return new ResponseEntity<List<ResponseEmpDTO>>(responseEmpList, HttpStatus.OK);
	}

	@GetMapping("/get/{eId}")
	public ResponseEntity<ResponseEmpDTO> getEmpById(@PathVariable("eId") int id) {
		Employee empById = empService.getEmpById(id);
		ResponseEmpDTO entityToResponseDTO = convertor.entityToResponseDTO(empById);
		return new ResponseEntity<ResponseEmpDTO>(entityToResponseDTO, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<ResponseEmpDTO> addEmp(@Valid @RequestBody EmployeeDTO empdto) {
		Employee emp = convertor.dtoToEntity(empdto);
		Employee savedEmployee = empService.save(emp);
		ResponseEmpDTO responseDTO = convertor.entityToResponseDTO(savedEmployee);
		return new ResponseEntity<ResponseEmpDTO>(responseDTO, HttpStatus.CREATED);
	}

	@PutMapping("/update/{eId}")
	public ResponseEntity<ResponseEmpDTO> updateEmp(@PathVariable("eId") int id, @Valid @RequestBody EmployeeDTO empDTO) {
		Employee employee = convertor.dtoToEntity(empDTO);
		Employee updatedEmployee = empService.update(id, employee);
		ResponseEmpDTO responseDTO = convertor.entityToResponseDTO(updatedEmployee);
		return new ResponseEntity<ResponseEmpDTO>(responseDTO, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{eId}")
	public void deleteEmp(@PathVariable("eId") int id) {
		empService.deleteById(id);
	}
}
