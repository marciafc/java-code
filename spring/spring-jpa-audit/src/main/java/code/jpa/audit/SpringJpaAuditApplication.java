package code.jpa.audit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import code.jpa.audit.start.Processamento;

@SpringBootApplication
public class SpringJpaAuditApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaAuditApplication.class, args);
	}
	@Bean
    public CommandLineRunner run(Processamento bean) throws Exception {
        return args -> {
        	bean.cargaInicial();
        	bean.alteraCidade();
        	bean.alteraCliente();
        };
    }

}
