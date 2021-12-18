package code.jasper.reports;

import java.util.ArrayList;
import java.util.List;

public class Sample {
	public static void main(String[] args) {
		JasperReportsGenerator generatorReport = JasperReportsGenerator.of();
		generatorReport.setReportName("IntegrantesRpts.jasper");
		generatorReport.setParameter("NOME_FANTASIA", "Digytal Code");
		generatorReport.setParameter("EMAIL", "gleyson@digytal.com.br");
		generatorReport.setParameter("TELEFONE", "(11) 95894-0362");
		
		
		List<Integrante> integrantes = new ArrayList<>();
		integrantes.add(new Integrante("GLEYSON SAMPAIO","glysns","glysns"));
		integrantes.add(new Integrante("FRANK MARLON","frankmms","frankmms"));
		integrantes.add(new Integrante("RAIMUNDO LOUREIRO","rsoft","rrsoft"));
		integrantes.add(new Integrante("ALOISIO CARVALHO","aloisiogit","aloisio_linkedin"));
	
		try {
			generatorReport.setData(integrantes);
			generatorReport.generateFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
