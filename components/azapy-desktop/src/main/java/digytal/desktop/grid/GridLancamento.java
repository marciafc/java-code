package digytal.desktop.grid;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import digytal.beans.Ctx;
import digytal.desktop.lancamento.FrmPagamento;
import digytal.util.Formato;
import digytal.util.desktop.ss.SSGrade;
import digytal.util.desktop.ss.SSMensagem;
import digytal.util.desktop.ss.evento.SSPesquisaListener;
import javax.swing.border.TitledBorder;

public class GridLancamento extends JPanel {
	private SSGrade grid = new SSGrade();
	public GridLancamento() {
		setBorder(new TitledBorder(null, "Lan\u00E7amentos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout());
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(grid);
		grid.adicionarColuna(0, "Data", "registro.data");
		grid.adicionarColuna(1, "Prox Vencto", "parcelamento.proximoVencimento.data");
		grid.adicionarColuna(2, "Classificação", "classificacao.nome");
		grid.adicionarColuna(3, "Descrição", "descricao");
		grid.adicionarColuna(4, "R$ Inicial", "valor.inicial");	
		grid.adicionarColuna(5, "R$ Acre +", "valor.acrescimo");	
		grid.adicionarColuna(6, "R$ Desc -", "valor.desconto");	
		grid.adicionarColuna(7, "R$ Pago", "valor.amortizado");	
		grid.adicionarColuna(8, "R$ Atual", "valor.atual");	
		grid.adicionarColuna(9, "R$ Parcl", "parcelamento.valorParcela");	
		grid.adicionarColuna(10, "Ut Vencto", "parcelamento.ultimoVencimento.data");
		grid.adicionarColuna(11, "R$ Pendt", "valor.pendente");	
		
		grid.getModeloColuna().setFormato(4, Formato.MOEDA);		
		grid.getModeloColuna().setFormato(5, Formato.MOEDA);		
		grid.getModeloColuna().setFormato(6, Formato.MOEDA);		
		grid.getModeloColuna().setFormato(7, Formato.MOEDA);		
		grid.getModeloColuna().setFormato(8, Formato.MOEDA);
		grid.getModeloColuna().setFormato(9, Formato.MOEDA);
		grid.getModeloColuna().setFormato(11, Formato.MOEDA);
		
		grid.definirLarguraColunas(50,60, 110, 120,60,60,60,60,60,60,60,60);
		//grid.setSize(800,600);
		add(scroll, BorderLayout.CENTER);
		
	}
	public void update(Object arrayList) {
		grid.setValue(arrayList);
	}
	public SSGrade getGrid() {
		return grid;
	}
}
