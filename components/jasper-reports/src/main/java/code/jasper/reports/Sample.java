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
		
		Integrante gleyson = new Integrante();
		gleyson.setNome("GLEYSON SAMPAIO");
		
		List<Integrante> integrantes = new ArrayList<>();
		integrantes.add(gleyson);
		try {
			generatorReport.setData(integrantes);
			generatorReport.generateFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
