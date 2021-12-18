package code.jasper.reports;

import java.io.File;
import java.net.URL;

import code.jasper.reports.util.TextUtil;

public class JasperReportsGenerator {
	private File reportPath;
	private String reportName;
	
	private File destinationPath;
	
	private File convertToFile(String ...paths) {
		String folder = TextUtil.concat("/", paths);
		return new File(folder);
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

	public void setResourceFolder(String ... paths) {
		String path = TextUtil.concat("/", paths);
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			URL resource = loader.getResource(path);
			reportPath = new File(resource.toURI());
			//LOG  de sua preferencia
			System.out.println(String.format("Pasta de relatórios %s localizada com sucesso !", reportPath));
		} catch (Exception e) {
			e.printStackTrace();
			//LOG / Exceção  de sua preferencia
			throw new RuntimeException(String.format("Não existe o diretorio %s na pasta resources do sistema", path));
		}
	}
	
	private JasperReportsGenerator() {
		
	}
	public static JasperReportsGenerator of() {
		JasperReportsGenerator generator = new JasperReportsGenerator();
		generator.setResourceFolder("reports");
		generator.setDestinationPath(System.getProperty("java.io.tmpdir"));
		return generator;
	}
}
