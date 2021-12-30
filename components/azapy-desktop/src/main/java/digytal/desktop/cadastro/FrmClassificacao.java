package digytal.desktop.cadastro;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.beans.Ctx;
import digytal.dto.Classificacao;
import digytal.enums.ClassificacaoMovimento;
import digytal.service.ClassificacaoService;
import digytal.util.desktop.Formulario;
import digytal.util.desktop.FormularioCadastro;
import digytal.util.desktop.ss.SSBotao;
import digytal.util.desktop.ss.SSCaixaCombinacao;
import digytal.util.desktop.ss.SSCampoTexto;
import digytal.util.desktop.ss.SSMensagem;
import digytal.util.desktop.ss.evento.SSPesquisaEvento;
import digytal.util.desktop.ss.evento.SSPesquisaListener;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmClassificacao extends FormularioCadastro {
	private SSBotao bSalvar = new SSBotao();
	private SSBotao bFechar= new SSBotao();
	private SSCampoTexto cPlanoConta = new SSCampoTexto();
	private SSCampoTexto cNome = new SSCampoTexto();
	private SSCaixaCombinacao cTipo = new SSCaixaCombinacao();
	private JCheckBox cFixo = new JCheckBox();
	private SSCampoTexto cClassificacao = new SSCampoTexto();
	
	@Autowired
    private ClassificacaoService service;
	private Classificacao registro;
	public Classificacao getRegistro() {
		return registro;
	}
	public FrmClassificacao() {
		cabecalho();
		campos();
		rodape();
		eventos();
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
		cPlanoConta.addPesquisaListener(new SSPesquisaListener() {
			@Override
			public void pesquisaListener(SSPesquisaEvento evento) {
				pesquisarPlanoContas();
			}
		});
		
	}
	private void pesquisarPlanoContas() {
		FrmLocalizaClassificacao frm = Ctx.getBean(FrmLocalizaClassificacao.class);
		Classificacao planoConta = (Classificacao) Formulario.dialogo(frm);	
		exibirPlanoConta(planoConta);
	}
	private void exibirPlanoConta(Classificacao pai) {
		registro.setPai(null);
		cPlanoConta.setValue("");
		if(pai!=null) {
			cPlanoConta.setValue(pai.getNome());
			registro.setPai(pai.getId());
		}
	}
	private void campos() {
		cPlanoConta.setRotulo("Pai");
		cNome.setRotulo("Nome");
		cTipo.setRotulo("Tipo");
		cFixo.setSelected(true);
		cFixo.setVerticalAlignment(SwingConstants.TOP);
		cFixo.setText("Fixo");
		cClassificacao.setRotulo("Código");
		cPlanoConta.setPesquisa(true);
		cPlanoConta.setEditavel(false, true);
		cPlanoConta.setColunas(45);
		cClassificacao.setColunas(8);
		cClassificacao.setEditavel(false);
		
		//cTipo.setMascara(Formato.MOEDA);
		//cTipo.setFormato(Formato.MOEDA);
		
		GridBagConstraints gbc_cOrigem = new GridBagConstraints();
		gbc_cOrigem.anchor = GridBagConstraints.NORTHWEST;
		gbc_cOrigem.gridwidth = 4;
		gbc_cOrigem.weightx = 1.0;
		gbc_cOrigem.insets = new Insets(3, 3, 5, 5);
		gbc_cOrigem.fill = GridBagConstraints.HORIZONTAL;
		gbc_cOrigem.gridx = 0;
		gbc_cOrigem.gridy = 0;
		
		getConteudo().add(cPlanoConta, gbc_cOrigem);
		
		GridBagConstraints gbc_cClassificacao = new GridBagConstraints();
		gbc_cClassificacao.anchor = GridBagConstraints.NORTHWEST;
		gbc_cClassificacao.fill = GridBagConstraints.HORIZONTAL;
		gbc_cClassificacao.insets = new Insets(3, 3, 3, 0);
		gbc_cClassificacao.gridx = 0;
		gbc_cClassificacao.gridy = 1;
		getConteudo().add(cClassificacao, gbc_cClassificacao);
		
		GridBagConstraints gbc_cNome = new GridBagConstraints();
		gbc_cNome.anchor = GridBagConstraints.NORTHWEST;
		gbc_cNome.weightx = 1.0;
		gbc_cNome.weighty = 1.0;
		gbc_cNome.insets = new Insets(3, 3, 3, 5);
		gbc_cNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNome.gridx = 1;
		gbc_cNome.gridy = 1;
		getConteudo().add(cNome, gbc_cNome);
		
		GridBagConstraints gbc_cTipo = new GridBagConstraints();
		gbc_cTipo.anchor = GridBagConstraints.NORTHWEST;
		gbc_cTipo.weighty = 1.0;
		gbc_cTipo.insets = new Insets(3, 3, 3, 0);
		gbc_cTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cTipo.gridx = 2;
		gbc_cTipo.gridy = 1;
		getConteudo().add(cTipo, gbc_cTipo);
		
		GridBagConstraints gbc_cFixo = new GridBagConstraints();
		gbc_cFixo.insets = new Insets(3, 3, 3, 3);
		gbc_cFixo.anchor = GridBagConstraints.SOUTHWEST;
		gbc_cFixo.weighty = 1.0;
		gbc_cFixo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cFixo.gridx = 3;
		gbc_cFixo.gridy = 1;
		getConteudo().add(cFixo, gbc_cFixo);
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
	public void setEntidade(Object entidade) {
		this.registro = (Classificacao) entidade;
		exibirRegistro();
	}
	@Override
	public void carregar() {
		cTipo.setItens(ClassificacaoMovimento.values(),"nome");
	}
	private void exibirRegistro() {
		cNome.setText(registro.nome);
		cTipo.setValue(registro.tipoMovimento);
		
	}
	
	@Override
	public void salvar() {
		try {
			if (SSMensagem.pergunta("Confirma salvar esta Classificação?")) {
				registro.nome = cNome.getText();
				registro.tipoMovimento = (ClassificacaoMovimento) cTipo.getValue();
				service.insert(registro);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
