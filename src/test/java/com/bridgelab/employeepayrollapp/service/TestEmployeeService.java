package com.bridgelab.employeepayrollapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.bridgelab.employeepayrollapp.model.Employee;
import com.bridgelab.employeepayrollapp.repository.EmployeeRepository;
import com.bridgelab.employeepayrollapp.services.EmployeeService;

@SpringBootTest
public class TestEmployeeService {

	@Mock
	private EmployeeRepository empRepository;

	@InjectMocks
	private EmployeeService service;

	Employee emp = new Employee();
	Employee emp1 = new Employee();
	Employee emp2 = new Employee();
	List<Employee> empList= null;

	@BeforeEach
	public void setUp() {
		List<String> department = new ArrayList<String>();
		department.add("HR");
		department.add("Sales");
		Employee emp = new Employee(1, "Pooja", "Female", 25000.0, LocalDate.now(), "employee1 added", "this pic ",department);
		Employee emp1 = new Employee(2, "Shreyas", "male", 35000, LocalDate.now(), "employee2 added", "pic", department);
		Employee emp2 = new Employee(2, "Shreyas", "male", 60000, LocalDate.now(), "employee1 updated", "pic", department);
		empList = new ArrayList<>();
		empList.add(emp1);
		empList.add(emp);
	}

	@Test
	public void testGetAllEmp() {
		
		when(empRepository.findAll()).thenReturn(empList);
		assertThat(empList).isEqualTo(service.getAllEmp());
		
	}
	
//	@Test
//	public void testGetAllEmpException() throws SQLException{
//		
//		when(empRepository.findAll()).thenThrow(SQLException.class);
//		assertThrows(DatabaseReadException.class, () ->service.getAllEmp());
//				//new RuntimeException("Something went wrong"));
//		
//	}

	@Test
    public void testFindEmpById() {
        doReturn(Optional.of(emp)).when(empRepository).findById(2);
        Employee returnedEntity = service.getEmpById(2);
        Assertions.assertTrue(returnedEntity != null, "Record was not found");
        Assertions.assertEquals(returnedEntity, emp, " REcords should be the same");
    }

	@Test
	public void testSave() {
		when(empRepository.save(emp)).thenReturn(emp);
		Employee savedEmp = service.save(emp);
		assertThat(emp).isEqualTo(savedEmp);
	}
	
	@Test
	public void updateEmpByIdTest() {
		when(empRepository.save(emp2)).thenReturn(emp2);
		doReturn(Optional.of(emp1)).when(empRepository).findById(2);
		Employee updateEmployee = service.update(2,emp2);
		assertThat(emp2).isEqualTo(updateEmployee);
	}
	
	@Test
	public void testDeleteById() {
		when(empRepository.existsById(1)).thenReturn(true);
		assertThat(true).isEqualTo(empRepository.existsById(1));
		
	}
}
