package code.send.email;

import java.io.File;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SendEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendEmailApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner run(EmailService sendEmail) throws Exception {
		return args -> {
			//local de um arquivo csv
			File file = new File("/digytal/emails.csv");
			List<String> linhas = FileReaderUtil.list(file.getAbsolutePath());
			linhas.forEach(linha->{
				String[] colunas = linha.split(";");
				Mensagem m = new Mensagem();
				m.setDestinatario(colunas[1]);
				m.setTitulo("Digytal Code - Exemplo de Envio de E-mail");
				String msg = String.format("%s\nAcabamos de enviar um e-mail com conteúdo \n%s ", colunas[0], colunas[2]);
				m.setCorpo(msg);
				sendEmail.send(m);
			});
			System.out.println("FIM ENVIO");
		};
	}
}
