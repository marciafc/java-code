package code.jasper.reports.component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;


import code.jasper.reports.util.FileUtil;
import code.jasper.reports.util.ReportFormat;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class JasperReportsGenerator {
	static final Locale BRAZIL_LOCALE = new Locale("pt", "BR");

	private File reportPath;
	private String reportName;
	private File destinationPath;
	private String destinationFile;
	
	private Map<String, Object> parameters = new HashMap<>();
	private Object data;
    
	public void setParameter(String name, Object value) {
        this.parameters.put(name, value);
    }
	public void setData(Object data) {
		this.data = data;
	}
	private File convertToFile(String ...paths) {
		return FileUtil.file("/", paths);
	}
	public void setReportPath(String ... paths) {
		this.reportPath = convertToFile(paths);
	}
	public void setDestinationPath(String ... paths) {
		this.destinationPath = convertToFile(paths);
	}
	
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public void setDestinationFile(String destinationFile) {
		this.destinationFile = destinationFile;
	}
	public void setResourceFolder(String ... paths) {
		try {
			reportPath = FileUtil.resource(paths);
			//LOG  de sua preferencia
			System.out.println(String.format("Pasta de relatórios %s localizada com sucesso !", reportPath));
		} catch (Exception e) {
			e.printStackTrace();
			//LOG / Exceção  de sua preferencia
			throw new RuntimeException(String.format("Não existe o diretorio %s na pasta resources do sistema", paths));
		}
	}
	
	private JasperReportsGenerator() {
		
	}
	public static JasperReportsGenerator of() {
		JasperReportsGenerator generator = new JasperReportsGenerator();
		generator.setResourceFolder("reports");
		generator.destinationPath = new File( System.getProperty("java.io.tmpdir"));
		generator.setDestinationFile(UUID.randomUUID().toString()+".pdf"); //ver classe code.jasper.reports.util.ReportFormat
		return generator;
	}
	public File generateFile() throws IOException, JRException {
        File file = new File(destinationPath, destinationFile);
        try (OutputStream output = new FileOutputStream(file)) {
            generate(output);
        }
        return file;
    }
	private void generate(OutputStream output) throws IOException, JRException {
		try {
			InputStream jasperStream = FileUtil.stream(reportPath, reportName);
			
			if (jasperStream == null) {
                throw new IllegalStateException(String.format("%s%s Arquivo .jasper não localizado ", reportPath.getAbsolutePath(), reportName));
            }

            parameters.put(JRParameter.REPORT_LOCALE, BRAZIL_LOCALE);
            parameters.put("REPORT_FORMAT", ReportFormat.PDF);
            parameters.put("EXPORTING_XLS", false);
            /*
            if (format == ReportFormat.EXCEL) {
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
            }
            */
            JRDataSource dataSource=null;
            if (dataSource == null && data != null) {
                if (data instanceof ResultSet) {
                    dataSource = new JRResultSetDataSource((ResultSet) data);
                } else {
                    Collection<?> collection = data instanceof Collection
                            ? (Collection<?>) data
                            : Collections.singleton(data);
                    dataSource = new JRBeanCollectionDataSource(collection);
                }
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, dataSource);
            generatePdf(jasperPrint, output);
        }catch (Exception e) {
			e.printStackTrace();
        }finally {
            output.close();
        }

    }
	private void generatePdf(JasperPrint jasperPrint, OutputStream output) throws JRException {
        JRPdfExporter pdfExporter = new JRPdfExporter();
        pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
        pdfExporter.exportReport();
    }
}
