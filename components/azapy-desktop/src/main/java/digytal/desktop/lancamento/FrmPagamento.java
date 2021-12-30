package digytal.desktop.lancamento;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.dto.Conta;
import digytal.dto.Pagamento;
import digytal.enums.ClassificacaoMovimento;
import digytal.service.ContaService;
import digytal.service.PagamentoService;
import digytal.util.Formato;
import digytal.util.desktop.FormularioCadastro;
import digytal.util.desktop.ss.SSBotao;
import digytal.util.desktop.ss.SSCaixaCombinacao;
import digytal.util.desktop.ss.SSCampoDataHora;
import digytal.util.desktop.ss.SSCampoNumero;
import digytal.util.desktop.ss.SSCampoTexto;
import digytal.util.desktop.ss.SSMensagem;


@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmPagamento extends FormularioCadastro {
	private SSCampoDataHora cDataVencimento = new SSCampoDataHora();
	private SSCampoDataHora cDataPagamento = new SSCampoDataHora();
	private SSCaixaCombinacao cConta = new SSCaixaCombinacao();
	private SSCaixaCombinacao cContaDestino = new SSCaixaCombinacao();
	
	private SSCampoNumero cValorParcela = new SSCampoNumero();
	private SSCampoNumero cValorPago = new SSCampoNumero();
	private SSCampoNumero cValorPendente = new SSCampoNumero();
	private SSCampoNumero cValorAcrescimo = new SSCampoNumero();
	private SSCampoNumero cValorDesconto = new SSCampoNumero();
	
	private SSCampoTexto cDescricao = new SSCampoTexto();
	private SSBotao bSalvar = new SSBotao();
	private SSBotao bFechar= new SSBotao();
	@Autowired
    private PagamentoService service;
	private Pagamento registro;
	
	@Autowired
    private ContaService contaService;
	
	public Pagamento getRegistro() {
		return registro;
	}
	public FrmPagamento() {
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
		cValorPago.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
            	calcular();
            }
        });     
	}
	private void calcular() {
    	Double p = cValorParcela.getDouble();
    	Double r = cValorPendente.getDouble();
    	Double a = cValorAcrescimo.getDouble();
		Double d = cValorDesconto.getDouble();
		if(p==null)
			p=0.0;
		if(r==null)
			r=0.0;
		if(a==null)
			a=0.0;
		if(d==null)
			d=0.0;
		
		cValorPago.setValue(p+r+a-d);
    }
	@Override
	public void carregar() {
		cConta.setItens(contaService.search(),"nome");
		cContaDestino.setItens(contaService.search(),"nome");
		
	}

	private void campos() {
		cDescricao.setRotulo("Descrição");
		cDataVencimento.setRotulo("Data - Vencto");
		cDataPagamento.setRotulo("Data - Pagto");
		
		cConta.setRotulo("Conta");
		cContaDestino.setRotulo("Conta Destino");
		cValorParcela.setRotulo("R$ Vl Parcela");
		cValorParcela.setMascara(Formato.MOEDA);
		cValorParcela.setFormato(Formato.MOEDA);
		
		cValorPago.setRotulo("R$ Vl Pago");
		cValorPago.setMascara(Formato.MOEDA);
		cValorPago.setFormato(Formato.MOEDA);
		
		cValorPendente.setRotulo("R$ Vl Pendente");
		cValorPendente.setMascara(Formato.MOEDA);
		cValorPendente.setFormato(Formato.MOEDA);
		
		cValorDesconto.setRotulo("R$ Desconto (-)");
		cValorDesconto.setMascara(Formato.MOEDA);
		cValorDesconto.setFormato(Formato.MOEDA);
		
		cValorAcrescimo.setRotulo("R$ Acréscimo (+)");
		cValorAcrescimo.setMascara(Formato.MOEDA);
		cValorAcrescimo.setFormato(Formato.MOEDA);
		
		cDescricao.setEditavel(false);
		cValorParcela.setEditavel(false);
		cDataVencimento.setEditavel(false);
		cValorPendente.setEditavel(false);
		cContaDestino.setEditavel(false);
	}
	private void conteudo() {
		setTitulo("Pagamento");
		setDescricao("Pagar Lançamento");
		GridBagConstraints gbc_cConta = new GridBagConstraints();
		gbc_cConta.weightx = 1.0;
		gbc_cConta.gridwidth = 2;
		gbc_cConta.anchor = GridBagConstraints.NORTHWEST;
		gbc_cConta.fill = GridBagConstraints.HORIZONTAL;
		gbc_cConta.insets = new Insets(3, 3, 0, 3);
		gbc_cConta.gridx = 0;
		gbc_cConta.gridy = 0;
		getConteudo().add(cConta, gbc_cConta);
		
		GridBagConstraints gbc_cDataVencimento = new GridBagConstraints();
		gbc_cDataVencimento.weightx = 1.0;
		gbc_cDataVencimento.gridwidth = 1;
		gbc_cDataVencimento.anchor = GridBagConstraints.NORTHWEST;
		gbc_cDataVencimento.fill = GridBagConstraints.HORIZONTAL;
		gbc_cDataVencimento.insets = new Insets(3, 3, 0, 3);
		gbc_cDataVencimento.gridx = 0;
		gbc_cDataVencimento.gridy = 1;
		getConteudo().add(cDataVencimento, gbc_cDataVencimento);
		
		GridBagConstraints gbc_cDataPagamento = new GridBagConstraints();
		gbc_cDataPagamento.weightx = 1.0;
		gbc_cDataPagamento.gridwidth = 1;
		gbc_cDataPagamento.anchor = GridBagConstraints.NORTHWEST;
		gbc_cDataPagamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_cDataPagamento.insets = new Insets(3, 3, 0, 3);
		gbc_cDataPagamento.gridx = 1;
		gbc_cDataPagamento.gridy = 1;
		getConteudo().add(cDataPagamento, gbc_cDataPagamento);
		
		GridBagConstraints gbc_cValorParcela = new GridBagConstraints();
		gbc_cValorParcela.weightx = 1.0;
		gbc_cValorParcela.gridwidth = 2;
		gbc_cValorParcela.anchor = GridBagConstraints.NORTHWEST;
		gbc_cValorParcela.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorParcela.insets = new Insets(3, 3, 0, 3);
		gbc_cValorParcela.gridx = 0;
		gbc_cValorParcela.gridy = 2;
		getConteudo().add(cValorParcela, gbc_cValorParcela);

		GridBagConstraints gbc_cValorPendente = new GridBagConstraints();
		gbc_cValorPendente.weightx = 1.0;
		gbc_cValorPendente.gridwidth = 2;
		gbc_cValorPendente.anchor = GridBagConstraints.NORTHWEST;
		gbc_cValorPendente.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorPendente.insets = new Insets(3, 3, 0, 3);
		gbc_cValorPendente.gridx = 0;
		gbc_cValorPendente.gridy = 3;
		getConteudo().add(cValorPendente, gbc_cValorPendente);
		
		GridBagConstraints gbc_cValorAcrescimo = new GridBagConstraints();
		gbc_cValorAcrescimo.weightx = 1.0;
		gbc_cValorAcrescimo.gridwidth = 2;
		gbc_cValorAcrescimo.anchor = GridBagConstraints.NORTHWEST;
		gbc_cValorAcrescimo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorAcrescimo.insets = new Insets(3, 3, 0, 3);
		gbc_cValorAcrescimo.gridx = 0;
		gbc_cValorAcrescimo.gridy = 4;
		getConteudo().add(cValorAcrescimo, gbc_cValorAcrescimo);

		GridBagConstraints gbc_cValorDesconto = new GridBagConstraints();
		gbc_cValorDesconto.weightx = 1.0;
		gbc_cValorDesconto.gridwidth = 2;
		gbc_cValorDesconto.anchor = GridBagConstraints.NORTHWEST;
		gbc_cValorDesconto.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorDesconto.insets = new Insets(3, 3, 0, 3);
		gbc_cValorDesconto.gridx = 0;
		gbc_cValorDesconto.gridy = 5;
		getConteudo().add(cValorDesconto, gbc_cValorDesconto);

		GridBagConstraints gbc_cValorPago = new GridBagConstraints();
		gbc_cValorPago.weightx = 1.0;
		gbc_cValorPago.gridwidth = 2;
		gbc_cValorPago.anchor = GridBagConstraints.NORTHWEST;
		gbc_cValorPago.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorPago.insets = new Insets(3, 3, 0, 3);
		gbc_cValorPago.gridx = 0;
		gbc_cValorPago.gridy = 6;
		getConteudo().add(cValorPago, gbc_cValorPago);
		setExibirNovo(false);
		GridBagConstraints gbc_cContaDestino = new GridBagConstraints();
		gbc_cContaDestino.weightx = 1.0;
		gbc_cContaDestino.gridwidth = 2;
		gbc_cContaDestino.anchor = GridBagConstraints.NORTHWEST;
		gbc_cContaDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_cContaDestino.insets = new Insets(3, 3, 0, 3);
		gbc_cContaDestino.gridx = 0;
		gbc_cContaDestino.gridy = 7;
		getConteudo().add(cContaDestino, gbc_cContaDestino);


	}
	private void rodape() {
		bSalvar.setText("Confirmar");
		bFechar.setText("Fechar");
		getRodape().add(bSalvar);
		getRodape().add(bFechar);
	}
	public void setEntidade(Object entidade) {
		this.registro = (Pagamento) entidade;
		this.cDescricao.setText(registro.descricao);
		this.cValorPendente.setValue(registro.valorPendente<0?0.0:registro.valorPendente);
		this.cDataVencimento.setData(registro.dataPagamento);
		this.cDataPagamento.setData(LocalDate.now());
		this.cValorParcela.setValue(registro.valorParcela);
		this.cValorDesconto.setValue(0.0);
		this.cValorAcrescimo.setValue(0.0);
		this.cValorPago.setValue(cValorParcela.getDouble()+cValorPendente.getDouble());
		this.cContaDestino.setEnabled(registro.tipoMovimento == ClassificacaoMovimento.T);
	}
	
	@Override
	public void salvar() {
		try {
			if (SSMensagem.pergunta("Confirma realizar este pagamento?")) {
				if(cValorPago.getDouble()> (cValorParcela.getDouble() + cValorPendente.getDouble())) {
					if(!SSMensagem.pergunta("Você esta pagando um valor maior, deseja prosseguir ?"))
						return;
				}
				Conta c = ((Conta) cConta.getValue());
				if(c==null) {
					SSMensagem.avisa("Informe a conta de pagamento");
					return;
				}
				this.registro.valorAcrescimo=cValorAcrescimo.getDouble();
				this.registro.valorDesconto = cValorDesconto.getDouble();
				this.registro.valorPago = cValorPago.getDouble();
				this.registro.conta = c.getId();
				this.registro.dataPagamento = cDataPagamento.getLocalDate();
				c = (Conta) cContaDestino.getValue();
				if(c!=null)
					this.registro.contaDestino =c.id;
				
				this.service.pagar(registro);
				fechar();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
