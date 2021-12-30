package digytal;

import java.util.List;

import javax.swing.UIManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import digytal.beans.Ctx;
import digytal.desktop.FrmLogin;
import digytal.service.ClassificacaoService;


@SpringBootApplication
public class CfipDesktopApplication {
		public static void main(String[] args) {
			try {
				String lf = UIManager.getSystemLookAndFeelClassName();
				UIManager.setLookAndFeel(lf);
				Ctx.exibirSplash();
				SpringApplication.run(CfipDesktopApplication.class, args);	
			} catch (Exception e) {
				//logger.error(e);
				System.exit(0);
			}
		}
		
		@Bean
		public CommandLineRunner run(FrmLogin login, ClassificacaoService s) {
			return args -> {
				login.exibir();
				
			};
		}

}
