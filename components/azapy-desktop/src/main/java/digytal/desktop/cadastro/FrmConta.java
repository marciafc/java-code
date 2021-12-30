package digytal.desktop.cadastro;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.dto.Conta;
import digytal.service.ContaService;
import digytal.util.Formato;
import digytal.util.desktop.FormularioCadastro;
import digytal.util.desktop.ss.SSBotao;
import digytal.util.desktop.ss.SSCampoNumero;
import digytal.util.desktop.ss.SSCampoTexto;
import digytal.util.desktop.ss.SSMensagem;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmConta extends FormularioCadastro {
	private SSBotao bSalvar = new SSBotao();
	private SSBotao bFechar= new SSBotao();
	private SSCampoNumero cSaldo = new SSCampoNumero();
	private SSCampoTexto cNome = new SSCampoTexto();
	
	private Conta registro;
	@Autowired
    private ContaService service;

	public FrmConta() {
		cabecalho();
		campos();
		rodape();
		eventos();
	}
	private void cabecalho() {
		setTitulo("Plano de Contas");
		setDescricao("Cadastro de Plano de Contas");
	}
	private void rodape() {
		bSalvar.setText("Salvar");
		bFechar.setText("Fechar");
		getRodape().add(bSalvar);
		getRodape().add(bFechar);
	}
	private void campos() {
		cSaldo.setRotulo("R$ Saldo");
		cNome.setRotulo("Nome");
		cSaldo.setMascara(Formato.MOEDA);
		cSaldo.setFormato(Formato.MOEDA);
		GridBagConstraints gbc_cNome = new GridBagConstraints();
		gbc_cNome.weightx = 1.0;
		gbc_cNome.gridwidth = 1;
		gbc_cNome.anchor = GridBagConstraints.NORTHWEST;
		gbc_cNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNome.insets = new Insets(3, 3, 0, 3);
		gbc_cNome.gridx = 0;
		gbc_cNome.gridy = 0;
		getConteudo().add(cNome, gbc_cNome);

		GridBagConstraints gbc_cSaldo = new GridBagConstraints();
		gbc_cSaldo.weighty = 1.0;
		gbc_cSaldo.weightx = 1.0;
		gbc_cSaldo.gridwidth = 1;
		gbc_cSaldo.anchor = GridBagConstraints.NORTHWEST;
		gbc_cSaldo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cSaldo.insets = new Insets(3, 3, 0, 3);
		gbc_cSaldo.gridx = 0;
		gbc_cSaldo.gridy = 1;
		getConteudo().add(cSaldo, gbc_cSaldo);



	}
	private void eventos() {
		bSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		
		bFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		
	}
	public void setEntidade(Object entidade) {
		this.registro = (Conta) entidade;
		
	}
	@Override
	public void salvar() {
		try {
			if (SSMensagem.pergunta("Confirma salvar a Plano de Contas?")) {
				registro.nome = cNome.getText();
				registro.saldo = cSaldo.getDouble(); 
				service.insert(registro);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public Conta getRegistro() {
		return registro;
	}
	

}
