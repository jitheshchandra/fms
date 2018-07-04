package com.ssrv.fms.service.reports;

import java.io.File;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class JasperReports	{

		Connection con;

		private String driverClassName = "com.mysql.jdbc.Driver";
		private String databaseUrl = "jdbc:mysql://localhost:3306/FMS";
		private String username = "root";
		private String password = "root";
		private String reportSourceFile = "";
		private String destinationSourceFile="";
		
		private HashMap<String, Object> params = new HashMap<String, Object>();
		
		public void addParams(HashMap<String, Object> getParams,String reportType)
		{
			try{
				
				 @SuppressWarnings("rawtypes")
				Iterator it = getParams.entrySet().iterator();
				    while (it.hasNext()) {
				        Map.Entry pairs = (Map.Entry)it.next();
				       params.put((String) pairs.getKey(),pairs.getValue());
				        it.remove(); // avoids a ConcurrentModificationException
				    }
				    reportSourceFile=reportType+".jrxml";
				    destinationSourceFile=reportType+".html";
				createReport();
				
			}catch(Exception ex){				
				ex.printStackTrace();
			}
		}
		
		public void createReport()
			{
				try
					{

						Class.forName(driverClassName);
						con = DriverManager.getConnection(databaseUrl, username, password);

						String reportSource = "C:/Users/JITHESH/Desktop/FMS/Reports/FMS_Reports/"+reportSourceFile;
						//String reportDest = "C:/Users/JITHESH/Desktop/FMS/Reports/FMS_Reports/"; //+destinationSourceFile;
						
						String currentDirPath = (Paths.get(".").toAbsolutePath().normalize().toString());
						boolean success = (new File(currentDirPath+"\\Reports")).mkdirs();
						
						if (!success) 
							System.out.println("Report Directory creatiion failed ");
						
						String reportDest = currentDirPath+"\\Reports"+destinationSourceFile;
						JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
						JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
						JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

						
							
						con.close();

					} catch (Exception ex)
					{
						ex.printStackTrace();
					}
			}

		public static void main(String[] args)
			{
				new JasperReports().createReport();
			}

	}
