package com.qburst.employeemanager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void addEmployee(EmployeeEntity employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);
	}
	public List<EmployeeEntity> listEmployee() {
		List<EmployeeEntity> employeeList = new ArrayList<EmployeeEntity>(); 
		employeeList = sessionFactory.getCurrentSession().createQuery("from EmployeeEntity where isdeleted is false")
				.list();
		return employeeList;
	}

	public EmployeeEntity getEmployee(Integer id) {
		EmployeeEntity employee = (EmployeeEntity) sessionFactory.getCurrentSession().get(EmployeeEntity.class, id);
				
		return employee;
	}

	public void deleteEmployee(Integer id) {
		EmployeeEntity employee = (EmployeeEntity) sessionFactory.getCurrentSession().load(
				EmployeeEntity.class, id);
		employee.setIsdeleted(true);
		if (null != employee) {
			sessionFactory.getCurrentSession().update(employee);
		}
	}

}
