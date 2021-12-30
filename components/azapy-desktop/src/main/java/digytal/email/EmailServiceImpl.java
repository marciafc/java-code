package digytal.email;

import org.springframework.stereotype.Component;
//https://www.baeldung.com/spring-email
//https://receitasdecodigo.com.br/spring-boot/como-configurar-projetos-spring-boot-para-enviar-e-mail

@Component
public class EmailServiceImpl {
	/*
	@Autowired
	private JavaMailSender mailSender;

	public void enviar() throws Exception {
		File file = new File("/digytal/notas-csv.csv");
		List<String> linhas = FileReaderUtil.list(file.getAbsolutePath());
		linhas.forEach(l->{
			String[] f = l.split(";");
			Mensagem m = new Mensagem();
			m.setDestinatario(f[2]);
			m.setTitulo("Digital House - Checkpoint - Feedback - 22/09/21");
			String msg = String.format("%s\nSua nota foi - Diagramação: %.2f / Implementação: %.2f \nConsiderações:\n%s ", 
					f[1],Double.valueOf(f[3].replaceAll("\\,", "\\.")),Double.valueOf(f[4].replaceAll("\\,", "\\.")),f[5]);
			m.setMensagem(msg);
			send(m);
		});
		System.out.println("FIM ENVIO");
	}

	private void send(Mensagem msg) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setText(msg.getMensagem());
		message.setTo(msg.getDestinatario());
		message.setFrom(msg.getRemetente());
		message.setSubject(msg.getTitulo());

		try {
			mailSender.send(message);
			System.out.println(msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	*/
}
