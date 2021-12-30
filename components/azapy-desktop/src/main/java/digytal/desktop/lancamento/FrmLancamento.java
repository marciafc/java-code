package digytal.desktop.lancamento;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.beans.Ctx;
import digytal.dto.Classificacao;
import digytal.dto.Lancamento;
import digytal.enums.Periodicidade;
import digytal.service.ClassificacaoService;
import digytal.service.LancamentoService;
import digytal.util.Formato;
import digytal.util.desktop.Formulario;
import digytal.util.desktop.FormularioCadastro;
import digytal.util.desktop.ss.SSBotao;
import digytal.util.desktop.ss.SSCaixaCombinacao;
import digytal.util.desktop.ss.SSCampoDataHora;
import digytal.util.desktop.ss.SSCampoNumero;
import digytal.util.desktop.ss.SSCampoTexto;
import digytal.util.desktop.ss.SSMensagem;
import digytal.util.desktop.ss.evento.SSPesquisaEvento;
import digytal.util.desktop.ss.evento.SSPesquisaListener;


@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmLancamento extends FormularioCadastro {
	private SSCampoDataHora cPrimeiroVencimento = new SSCampoDataHora();
	private SSCaixaCombinacao cClassificacao = new SSCaixaCombinacao();
	private SSCampoNumero cValor = new SSCampoNumero();
	private SSCampoTexto cDescricao = new SSCampoTexto();
	private SSBotao bSalvar = new SSBotao();
	private SSBotao bFechar= new SSBotao();
	@Autowired
    private LancamentoService service;
	private Lancamento registro;
	
	@Autowired
    private ClassificacaoService classificacaoService;
	
	public Lancamento getRegistro() {
		return registro;
	}
	public FrmLancamento() {
		conteudo();
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
		
		cValor.addPesquisaListener(new SSPesquisaListener() {
			@Override
			public void pesquisaListener(SSPesquisaEvento evento) {
				showLancamentoDetalhe();
			}
		});
		
	}
	@Override
	public void carregar() {
		cClassificacao.setItens(classificacaoService.search(),"nome");
	}
	
	private void showLancamentoDetalhe() {
		FrmProgramacao frm = Ctx.getBean(FrmProgramacao.class);
		frm.setLancamento(registro);
		Formulario.dialogo(frm);
		if(registro.valor!=null && registro.valor > 0.0d) {
			cValor.setValue(registro.valor);
			cValor.setEditavel(false, true);
		}else
			cValor.setValue(0.0d);
		
		
	}
	private void campos() {
		cDescricao.setRotulo("Descrição");
		cPrimeiroVencimento.setRotulo("Data - Vencto");
		cClassificacao.setRotulo("Classificação");
		cValor.setRotulo("R$ Valor");
		cValor.setMascara(Formato.MOEDA);
		cValor.setFormato(Formato.MOEDA);
		
		cValor.setColunas(10);
		cPrimeiroVencimento.setData(LocalDate.now());
		
		cValor.setPesquisa(true);
		cValor.setEditavel(true, true);
	}
	private void conteudo() {
		setTitulo("Lançamento");
		setDescricao("Novo Lançamento");
		GridBagConstraints gbc_cClassificacao = new GridBagConstraints();
		gbc_cClassificacao.weightx = 1.0;
		gbc_cClassificacao.gridwidth = 2;
		gbc_cClassificacao.anchor = GridBagConstraints.NORTHWEST;
		gbc_cClassificacao.fill = GridBagConstraints.HORIZONTAL;
		gbc_cClassificacao.insets = new Insets(3, 3, 0, 3);
		gbc_cClassificacao.gridx = 0;
		gbc_cClassificacao.gridy = 0;
		getConteudo().add(cClassificacao, gbc_cClassificacao);
		
		GridBagConstraints gbc_cDescricao = new GridBagConstraints();
		gbc_cDescricao.weightx = 1.0;
		gbc_cDescricao.gridwidth = 2;
		gbc_cDescricao.anchor = GridBagConstraints.NORTHWEST;
		gbc_cDescricao.fill = GridBagConstraints.HORIZONTAL;
		gbc_cDescricao.insets = new Insets(3, 3, 0, 3);
		gbc_cDescricao.gridx = 0;
		gbc_cDescricao.gridy = 1;
		getConteudo().add(cDescricao, gbc_cDescricao);

		GridBagConstraints gbc_cData = new GridBagConstraints();
		gbc_cData.fill = GridBagConstraints.HORIZONTAL;
		gbc_cData.weightx = 1.0;
		gbc_cData.anchor = GridBagConstraints.NORTHWEST;
		gbc_cData.insets = new Insets(3, 3, 3, 0);
		gbc_cData.gridx = 0;
		gbc_cData.gridy = 2;
		getConteudo().add(cPrimeiroVencimento, gbc_cData);
		
		
		GridBagConstraints gbc_cValor = new GridBagConstraints();
		gbc_cValor.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValor.weightx = 1.0;
		gbc_cValor.weighty = 1.0;
		gbc_cValor.anchor = GridBagConstraints.NORTHEAST;
		gbc_cValor.insets = new Insets(3, 3, 3, 3);
		gbc_cValor.gridx = 1;
		gbc_cValor.gridy = 2;
		getConteudo().add(cValor, gbc_cValor);


	}
	private void rodape() {
		bSalvar.setText("Salvar");
		bFechar.setText("Fechar");
		getRodape().add(bSalvar);
		getRodape().add(bFechar);
	}
	public void setEntidade(Object entidade) {
		this.registro = (Lancamento) entidade;
	}
	
	@Override
	public void salvar() {
		try {
			if (SSMensagem.pergunta("Confirma incluir este lançamento?")) {
				registro.data = LocalDate.now();
				registro.proximoVencimento = cPrimeiroVencimento.getLocalDate();
				registro.descricao=cDescricao.getText();
				
				Classificacao c = (Classificacao) cClassificacao.getValue();
				
				registro.classificacao = c.id; 
				registro.valor=cValor.getDouble();
				service.insert(registro);
				SSMensagem.informa("Lançamento salvo com sucesso!");
				fechar();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
