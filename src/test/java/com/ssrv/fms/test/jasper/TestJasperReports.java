package com.ssrv.fms.test.jasper;

import java.util.HashMap;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class TestJasperReports
	{

		/**
		 * @param args
		 *            the command line arguments
		 */
		public static void main(String[] args)
			{
				// TODO code application logic here

				String reportSource = "D:/WD/JasperReports/TestReport.jrxml";
				String reportDest = "D:/WD/JasperReports/TestReport.html";

				HashMap<String, Object> params = new HashMap<String, Object>();

				try
					{
						JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

						JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

						JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

						// JasperViewer.viewReport(jasperPrint);
					} catch (JRException ex)
					{
						ex.printStackTrace();
					}
			}

	}
