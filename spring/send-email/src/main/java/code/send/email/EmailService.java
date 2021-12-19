package code.send.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;
	public void send(Mensagem msg) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(msg.getRemetente());
		message.setTo(msg.getDestinatario());
		message.setSubject(msg.getTitulo());
		message.setText(msg.getCorpo());
		try {
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
