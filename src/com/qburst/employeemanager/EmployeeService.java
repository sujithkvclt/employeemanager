package com.qburst.employeemanager;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {
	public void addEmployee(Employee employee);

	public Employee getEmployee(Integer id);

	public void deleteEmployee(Integer id);

	public List<Employee> listEmployee();
	
	public void saveImageInDatasae(Employee employee,MultipartFile file);
	
	public void saveImageAsFile(Employee employee,MultipartFile file);
	
	public void validateEmployee(Employee employee,BindingResult bindingResult);
	
	public  byte[] generatePdfReport(Employee employee);
	
}
