package digytal.desktop.grid;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import digytal.dto.view.Lancamentos;
import digytal.util.Formato;
import digytal.util.desktop.ss.SSCampoNumero;
import digytal.util.desktop.ss.SSGrade;

public class GridProgramacao1 extends JPanel {
	private SSCampoNumero cInicial = new SSCampoNumero();
	private SSCampoNumero cAtual = new SSCampoNumero();
	private SSGrade cGrade= new SSGrade();
	
	public GridProgramacao1() {
		setLayout(new BorderLayout());
		JScrollPane scroll = new JScrollPane();
		cGrade.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scroll.setViewportView(cGrade);
		
		cGrade.adicionarColuna(0, "Data", "detalhe.registro.data");
		cGrade.adicionarColuna(1, "Vencto", "detalhe.vencimento.data");
		cGrade.adicionarColuna(2, "Classificação", "classificacao.nome");
		cGrade.adicionarColuna(3, "Descrição", "detalhe.descricao");
		cGrade.adicionarColuna(4, "R$ Inicial", "detalhe.valor.inicial");	
		cGrade.adicionarColuna(5, "R$ Atual", "detalhe.valor.atual");	
		
		cGrade.getModeloColuna().setFormato(5, Formato.MOEDA);		
		cGrade.getModeloColuna().setFormato(5, Formato.MOEDA);		
		
		cGrade.definirLarguraColunas(50,50, 100, 200,60,60);
		
		add(scroll,BorderLayout.CENTER);
		JPanel txts = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		txts.add(cInicial);
		txts.add(cAtual);
		
	}
	public void setLancamentos(List<Lancamentos> lancamentos) {
		cGrade.setValue(lancamentos);
	}
}
