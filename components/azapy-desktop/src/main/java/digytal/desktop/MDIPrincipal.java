package digytal.desktop;

import org.springframework.stereotype.Component;

import digytal.beans.Ctx;
import digytal.util.desktop.MDI;

@Component
public class MDIPrincipal extends MDI {
	public MDIPrincipal() {
		
		
	}
	
	public void iniciar() {
		FrmPrincipal bean = Ctx.getBean(FrmPrincipal.class);
		bean.load();
		exibir(bean);
	}
}
