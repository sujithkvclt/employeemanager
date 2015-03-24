package com.qburst.employeemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private Validator validator;

	@Autowired
	private EmployeeServiceHelper employeeServiceHelper;

	@Transactional
	public void addEmployee(Employee employee) {
		employeeDAO.addEmployee((EmployeeEntity) employeeServiceHelper.map(
				employee, EmployeeEntity.class));
	}

	@Transactional
	public Employee getEmployee(Integer id) {
		Employee employee = employeeServiceHelper.map(
				employeeDAO.getEmployee(id), Employee.class);
		return employee;
	}

	@Transactional
	public void deleteEmployee(Integer id) {
		employeeDAO.deleteEmployee(id);
	}

	@Transactional
	public List<Employee> listEmployee() {
		List<Employee> list = employeeServiceHelper.mapCollection(
				employeeDAO.listEmployee(), Employee.class);
		return list;
	}

	public void saveImage(Employee employee, MultipartFile file) {
		byte[] bFile;
		String imageBASE64;
		try {
			bFile = file.getBytes();
			imageBASE64 = DatatypeConverter.printBase64Binary(bFile);
		} catch (IOException e) {
			imageBASE64 = "";
		}
		if (imageBASE64.isEmpty()) {
			String fileName = "/default_imageBASE64.txt";
			try {
				ClassLoader.class
						.getResourceAsStream("default_imageBASE64.txt");
				BufferedReader br = new BufferedReader(new FileReader(""));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		employee.setImage(imageBASE64);

	}

	private boolean validateFileExtension(String extension) {
		if (extension.equals("jpg") || extension.equals("png")
				|| extension.equals("bmp") || extension.equals("gif")) {
			return true;
		} else {
			return false;
		}

	}

	public void saveImageAsFile(Employee employee, MultipartFile file) {
		try {
			if (!file.isEmpty()) {

				String rootPath = System.getProperty("catalina.home");
				String[] originalFileName = StringUtils.split(
						file.getOriginalFilename(), ".");
				String fileExtension = originalFileName[originalFileName.length - 1];
				if (validateFileExtension(fileExtension)) {
					File newFile = new File(rootPath + "/image_uploads/"
							+ employee.getName() + "." + fileExtension);
					if (!newFile.exists())
						newFile.mkdirs();
					file.transferTo(newFile);
					employee.setImage("/uploads/" + employee.getName() + "."
							+ fileExtension);
				} else {
					if (employee.getImage() == null) {
						employee.setImage("/uploads/default.jpg");
					}
				}
			} else {
				if(employee.getImage()==null){
					employee.setImage("/uploads/default.jpg");
				}
				
			}
		} catch (IOException e) {
		}
	}

	public void validateEmployee(Employee employee, BindingResult bindingResult) {
		EmployeeEntity employeeEntity = (EmployeeEntity) employeeServiceHelper
				.map(employee, EmployeeEntity.class);
		validator.validate(employeeEntity, bindingResult);
	}

	@Override
	public void saveImageInDatasae(Employee employee, MultipartFile file) {
		byte[] bFile;
		String imageBASE64;
		try {
			bFile = file.getBytes();
			imageBASE64 = DatatypeConverter.printBase64Binary(bFile);
		} catch (IOException e) {
			imageBASE64 = "";
		}
		if (imageBASE64.isEmpty()) {
			String fileName = "/default_imageBASE64.txt";
			try {
				ClassLoader.class
						.getResourceAsStream("default_imageBASE64.txt");
				BufferedReader br = new BufferedReader(new FileReader(""));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}

	}
	public  byte[] generatePdfReport(Employee employee){
		EmployeePdfView employeePdfView = new EmployeePdfView(employee);
		byte[] pdfBytes = employeePdfView.generatePdfBytes();
		return pdfBytes;
	}

}
