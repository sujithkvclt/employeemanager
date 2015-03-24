package com.qburst.employeemanager;

import java.util.List;

public interface EmployeeDAO {

	public void addEmployee(EmployeeEntity employee);

	public EmployeeEntity getEmployee(Integer id);

	public void deleteEmployee(Integer id);

	public List<EmployeeEntity> listEmployee();

}
