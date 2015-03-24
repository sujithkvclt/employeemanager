package com.qburst.employeemanager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

//@Component
public class EmployeePdfView {
	public EmployeePdfView(Employee employee) {
		super();
		this.employee = employee;
	}

	// @Value("${upload.url}")
	private String baseUrl = "/home/sujith/apache-tomcat-7.0.53/image_uploads";
	private Employee employee;

	public byte[] generatePdfBytes() {

		try {

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			Document document = new Document();
			PdfWriter.getInstance(document, byteArrayOutputStream);
			document.open();
			document.add(getPdfTable());
			document.close();
			byte[] pdfBytes = byteArrayOutputStream.toByteArray();
			return pdfBytes;
		} catch (Exception e) {
		}
		return null;
	}

	private  PdfPTable getPdfTable() throws IOException, DocumentException{
		 PdfPTable table = new PdfPTable(3);
		 float[] columnWidths = new float[] {66f,130f,130f};
		 table.setWidths(columnWidths);
	        PdfPCell cell;
	        String imageUrl = employee.getImage();
	       	String subString = imageUrl.substring(8);
	       	imageUrl=baseUrl + subString;
	        Image image = Image.getInstance(imageUrl);
	        image.scaleAbsolute(80,100);
	        cell = new PdfPCell(image);
	        cell.setRowspan(6);
	        cell.setPadding(2);
	        table.addCell(cell);
	        table.addCell("Name");
	        table.addCell(employee.getName());
	        table.addCell("Employe Code");
	        table.addCell(employee.getCode());
	        table.addCell("Designation");
	        table.addCell(employee.getDesignation());
	        table.addCell("Email");
	        table.addCell(employee.getEmail());
	        table.addCell("Phone Number");
	        table.addCell(employee.getPhone());
	        table.addCell("Location");
	        table.addCell(employee.getLocation());
	        return table;
		
	}
}
