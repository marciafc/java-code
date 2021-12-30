package digytal.desktop.lancamento;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.dto.Lancamento;
import digytal.entity.embedded.Parcelamento;
import digytal.enums.Periodicidade;
import digytal.util.Formato;
import digytal.util.desktop.Formulario;
import digytal.util.desktop.ss.SSBotao;
import digytal.util.desktop.ss.SSCaixaCombinacao;
import digytal.util.desktop.ss.SSCampoNumero;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmProgramacao extends Formulario {
	private SSCaixaCombinacao cPeriodicidade = new SSCaixaCombinacao();
	private SSCampoNumero cQuantidadeParcelas = new SSCampoNumero();
	private SSCampoNumero cValorParcela = new SSCampoNumero();
	private SSCampoNumero cProximaParcela = new SSCampoNumero();
	private SSCampoNumero cValorTotal = new SSCampoNumero();
	private SSBotao bSalvar = new SSBotao();
	private SSBotao bFechar= new SSBotao();
	private Lancamento lancamento;
	public FrmProgramacao() {
		conteudo();
		campos();
		rodape();
		
	}
	private void conteudo() {
		setTitulo("Programação");
		setDescricao("Programação de Pagamento");
		
		GridBagConstraints gbc_cPeriodicidade = new GridBagConstraints();
		gbc_cPeriodicidade.weightx = 1.0;
		gbc_cPeriodicidade.anchor = GridBagConstraints.NORTHWEST;
		gbc_cPeriodicidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_cPeriodicidade.gridwidth = 4;
		gbc_cPeriodicidade.insets = new Insets(3, 3, 0, 3);
		gbc_cPeriodicidade.gridx = 0;
		gbc_cPeriodicidade.gridy = 0;
		getConteudo().add(cPeriodicidade, gbc_cPeriodicidade);
		
		GridBagConstraints gbc_cValorParcela = new GridBagConstraints();
		gbc_cValorParcela.weighty = 1.0;
		gbc_cValorParcela.insets = new Insets(3, 3, 3, 0);
		gbc_cValorParcela.anchor = GridBagConstraints.NORTHWEST;
		gbc_cValorParcela.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorParcela.gridx = 0;
		gbc_cValorParcela.gridy = 1;
		getConteudo().add(cValorParcela, gbc_cValorParcela);
		
		GridBagConstraints gbc_cParcelas = new GridBagConstraints();
		gbc_cParcelas.anchor = GridBagConstraints.NORTHWEST;
		gbc_cParcelas.fill = GridBagConstraints.HORIZONTAL;
		gbc_cParcelas.insets = new Insets(3, 3, 3, 0);
		gbc_cParcelas.gridx = 1;
		gbc_cParcelas.gridy = 1;
		getConteudo().add(cQuantidadeParcelas, gbc_cParcelas);
		
		GridBagConstraints gbc_cNumeroParcela = new GridBagConstraints();
		gbc_cNumeroParcela.gridwidth = 1;
		gbc_cNumeroParcela.anchor = GridBagConstraints.NORTHWEST;
		gbc_cNumeroParcela.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNumeroParcela.insets = new Insets(3, 3, 0, 0);
		gbc_cNumeroParcela.gridx = 2;
		gbc_cNumeroParcela.gridy = 1;
		getConteudo().add(cProximaParcela, gbc_cNumeroParcela);


		GridBagConstraints gbc_cValorTotal = new GridBagConstraints();
		gbc_cValorTotal.anchor = GridBagConstraints.NORTHWEST;
		gbc_cValorTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorTotal.insets = new Insets(3, 3, 3, 3);
		gbc_cValorTotal.gridx = 3;
		gbc_cValorTotal.gridy = 1;
		getConteudo().add(cValorTotal, gbc_cValorTotal);
		
				eventos();
	}
	private void eventos() {
		bSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok();
			}
		});
		
		
		bFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		
		
		cValorTotal.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
            	calcular();
            }
        });       
	}
	private void ok() {
		lancamento.parcelamento = new Parcelamento();
		lancamento.valor = cValorTotal.getDouble();
		lancamento.parcelamento.setQuantidadeParcelas(cQuantidadeParcelas.getInteger());;
		lancamento.parcelamento.setValorParcela(cValorParcela.getDouble());
		lancamento.parcelamento.setProximaParcela(cProximaParcela.getInteger());
		lancamento.periodicidade = (Periodicidade) cPeriodicidade.getValue();
		lancamento.programado=true;
		fechar(lancamento);
	}
	@Override
	public void carregar() {
		cPeriodicidade.setItens(Periodicidade.values(),"nome");
		cPeriodicidade.setValue(Periodicidade.M);
	}
	private void campos() {
		cPeriodicidade.setRotulo("Ronovação");
		cValorParcela.setRotulo("R$ Parcela");
		cValorTotal.setRotulo("R$ Total");
		//cValorTotal.setEditavel(false);
		cQuantidadeParcelas.setRotulo("Nº");
		cProximaParcela.setRotulo("1º");
		cQuantidadeParcelas.setColunas(3);
		cProximaParcela.setColunas(3);
		
		cValorParcela.setMascara(Formato.MOEDA);
		cValorTotal.setMascara(Formato.MOEDA);
		cValorParcela.setFormato(Formato.MOEDA);
		cValorTotal.setFormato(Formato.MOEDA);
	}
	private void rodape() {
		bSalvar.setText("Confirmar");
		bFechar.setText("Fechar");
		getRodape().add(bSalvar);
		getRodape().add(bFechar);
	}
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	
	private void calcular() {
    	Double p = cQuantidadeParcelas.getDouble();
    	Double n = cProximaParcela.getDouble();
		Double v = cValorParcela.getDouble();
		if(p==null)
			p=0.0;
		if(v==null)
			v=0.0;
		cValorTotal.setValue((p+1-n) * v);
    }
	

}
