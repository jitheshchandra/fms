package com.ssrv.fms.test.jasper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class JasperReportsWithParams
	{

		/**
		 * TODO: pom dependency added. !
		 * 
		 * <dependency> <groupId>net.sf.jasperreports</groupId>
		 * <artifactId>jasperreports</artifactId> <version>5.0.1</version>
		 * </dependency>
		 * 
		 * -- Remove languar='groovy' from all the jrxml or jasper reports.
		 * 
		 */

		Connection con;

		private String driverClassName = "com.mysql.jdbc.Driver";
		private String databaseUrl = "jdbc:mysql://localhost:3306/FMS";
		private String username = "root";
		private String password = "root";

		public void createReport()
			{
				try
					{

						/**
						 * TODO: Move this to get the connection from the
						 * datasource.
						 */
						Class.forName(driverClassName);
						con = DriverManager.getConnection(databaseUrl, username, password);

						/**
						 * have map of jrxml file for the corresponding report.
						 * from any particular location, DB ???
						 */
						String reportSource = "D:/WD/JasperReports/FMS_Reports/EmployeeAttendence.jrxml";
						String reportDest = "D:/WD/JasperReports/EmployeeAttendence.html";
						String reportDestPdf = "D:/WD/JasperReports/EmployeeAttendence.pdf";

						/**
						 * TODO: Pass the params from the jsp. ?
						 */
						HashMap<String, Object> params = new HashMap<String, Object>();
						params.put("ReportingMonth", "11");
						params.put("ReportYear", "2014");
						params.put("Organization", "1");
						params.put("Branch", "8");

						JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
						JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
						JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
						JasperExportManager.exportReportToPdfFile(jasperPrint, reportDestPdf);

						/**
						 * the below may not be required, if used datasource. ?
						 */
						con.close();

						// JasperViewer.viewReport(jasperPrint);
					} catch (Exception ex)
					{
						ex.printStackTrace();
					}
			}

		public static void main(String[] args)
			{
				new JasperReportsWithParams().createReport();
			}

	}
