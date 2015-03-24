package com.qburst.employeemanager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class EmployeeEntity {

	@Id
	@Column(name = "emp_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_emp_id_seq")
	@SequenceGenerator(name = "employee_emp_id_seq", sequenceName = "employee_emp_id_seq", allocationSize = 1)
	private Integer id;

	@Column(name = "emp_name")
	@Size(min = 2, max = 30)
	private String name;

	@Column(name = "emp_code")
	@Pattern(regexp = "QB[0-9]+", message = "Code must start with QB")
	private String code;

	@Column(name = "emp_designation")
	private String designation;

	@Column(name = "emp_location")
	private String location;

	@Column(name = "emp_isdeleted")
	private boolean isdeleted;

	@Column(name = "emp_email")
	@Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}", message = "Invalid email address.")
	private String email;

	@Column(name = "emp_phone")
	@Pattern(regexp = "[0-9]{10}", message = "must be 10 digit number.")
	private String phone;

	@Column(name = "emp_image")
	private String image;

	public EmployeeEntity() {

	};

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}