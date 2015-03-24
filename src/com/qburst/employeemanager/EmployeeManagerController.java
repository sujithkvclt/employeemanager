package com.qburst.employeemanager;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeManagerController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/hello")
	public String helloWorld(ModelMap map) {
		String message = "HELLO SPRING MVC HOW R U";
		map.addAttribute("message", message);
		return "hellopage";

	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String listEmployee(ModelMap map, HttpServletRequest request)
			throws IOException {
		List<Employee> employeeList = employeeService.listEmployee();
		map.addAttribute("employeeList", employeeList);
		return "home";
	}

	@RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
	public String newEmployee(ModelMap map) {
		Employee newEmployee = new Employee();
		map.addAttribute("employee", newEmployee);

		return "employeeForm";
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@RequestParam("file") MultipartFile file,
			Employee employee, BindingResult bindingResult) {

		employeeService.saveImageAsFile(employee, file);
		employeeService.validateEmployee(employee, bindingResult);
		if (bindingResult.hasErrors()) {
			return new ModelAndView("employeeForm");
		}
		employeeService.addEmployee(employee);
		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		employeeService.deleteEmployee(id);
		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public ModelAndView editEmployee(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = employeeService.getEmployee(id);
		ModelAndView model = new ModelAndView("employeeForm");
		model.addObject("employee", employee);
		return model;
	}
	@RequestMapping(value = "/getPdfReport", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPdfReport(HttpServletRequest request,HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = employeeService.getEmployee(id);
		byte[] pdfBytes = employeeService.generatePdfReport(employee);
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    String filename = "output.pdf";
	    headers.setContentDispositionFormData(filename, filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(pdfBytes, headers, HttpStatus.OK);
	    return responseEntity;
	}
}
