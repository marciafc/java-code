package code.excel.resultset.sample;

import java.io.File;
import java.time.LocalDate;

import code.excel.resultset.component.ExcelResultSet;
import code.excel.resultset.model.Sexo;

public class ExcelResultSetSample {
	public static void main(String[] args) {
		try {
			File file = new File(Thread.currentThread().getContextClassLoader().getResource("instrutor.xlsx").toURI());
			ExcelResultSet exRs = new ExcelResultSet(file);

			while (exRs.next()) {
				String cpf = exRs.getString("Cpf");
				String nome = exRs.getString("Nome");
				LocalDate dtNascimento = exRs.getLocalDate("Data Nascimento");
				Sexo sexo = Sexo.valueOf(exRs.getString("Sexo").toUpperCase());
				Double valorHora = exRs.getDouble("Valor Hora");
				Boolean brasileiro = exRs.getBoolean("Brasileiro?", "S");

				System.out.println(String.format(
						"O Cadastro %s de Cpf %s, nascido em %s, no País %s do Sexo %s, com valor hora R$ %.2f ", nome,
						cpf, dtNascimento.toString(), brasileiro ? "BRASIL" : "EXTERIOR", sexo.getDescricao(),
						valorHora));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
