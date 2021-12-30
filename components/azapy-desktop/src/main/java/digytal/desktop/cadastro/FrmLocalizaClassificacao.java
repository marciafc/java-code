package digytal.desktop.cadastro;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.dto.Classificacao;
import digytal.service.ClassificacaoService;
import digytal.util.desktop.FormularioConsulta;
import digytal.util.desktop.ss.SSBotao;
import digytal.util.desktop.ss.SSCampoTexto;
import digytal.util.desktop.ss.SSMensagem;
import digytal.util.desktop.ss.SSPosicaoRotulo;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmLocalizaClassificacao extends FormularioConsulta {
	private SSBotao bOk = new SSBotao();
	private SSBotao bFechar = new SSBotao();
	private SSCampoTexto cNome = new SSCampoTexto();
	
	@Autowired
	private ClassificacaoService service;
	
	public FrmLocalizaClassificacao() {
		init();
	}

	public void init() {
		super.setTitulo("Plano de Contas");
		super.setDescricao("Localiza Plano de Contas");
		bFechar.setText("Sair");
		bFechar.setIcone("fechar");
		bOk.setText("OK");
		
		cNome.setRotulo("Nome");
		cNome.setColunas(30);
		cNome.setRotuloPosicao(SSPosicaoRotulo.ESQUERDA);
		
		JPanel filtros = new JPanel(new GridBagLayout());
		GridBagConstraints gbc_cNome = new GridBagConstraints();
		gbc_cNome.weightx = 1.0;
		gbc_cNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNome.insets = new Insets(3, 3, 0, 0);
		gbc_cNome.anchor = GridBagConstraints.NORTHWEST;
		gbc_cNome.gridx = 0;
		gbc_cNome.gridy = 0;
		filtros.add(cNome, gbc_cNome);
		
		filtros.add(cmdBuscar, getGbcBuscar(1, 0));
		getConteudo().add(filtros,BorderLayout.NORTH);
	
			
		getTabela().adicionarColuna(0, "Código", "classificacao");
		getTabela().adicionarColuna(1, "Nome", "nome");
		getTabela().definirLarguraColunas(50, 270);
		getRodape().add(bOk);
		getRodape().add(bFechar);
		
		super.setAlinhamentoBotoes(FlowLayout.RIGHT);
		//super.setFiltros(pFiltros, 1, 0);
	
		bOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok();
			}
		});
		
		
		bFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		
	}
	
	private void ok() {
		Classificacao entidade = (Classificacao) getTabela().getLinhaSelecionada();
		fechar(entidade);
	}

	public void filtrar() {
		try {
			String nome = cNome.getText();
			if (nome.isEmpty()) {
				//SSMensagem.avisa("Informe um nome para a pesquisa");
				//return;
			}
			List<Classificacao> lista = service.search();
			if (lista.size() == 0) {
				SSMensagem.informa("Não há itens com este nome");
			}
			getTabela().setValue(lista);
		} catch (Exception e) {
			SSMensagem.erro(e.getMessage());
		}

	}

	
}