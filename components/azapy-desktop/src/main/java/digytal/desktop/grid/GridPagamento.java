package digytal.desktop.grid;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import digytal.util.Formato;
import digytal.util.desktop.ss.SSGrade;

public class GridPagamento extends JPanel {
	private SSGrade grid = new SSGrade();
	public GridPagamento() {
		setBorder(new TitledBorder(null, "Pagamentos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		setLayout(new BorderLayout());
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(grid);
		grid.adicionarColuna(0, "Data", "registro.data");
		grid.adicionarColuna(1, "Pagto", "pagamento.data");
		grid.adicionarColuna(2, "Movto", "classificacao.tipoMovimento.nome");
		grid.adicionarColuna(3, "Classificação", "classificacao.nome");
		grid.adicionarColuna(4, "Descrição", "descricao");
		grid.adicionarColuna(5, "R$ Saldo", "saldo");	
		grid.adicionarColuna(6, "R$ Pago", "valor.amortizado");	
		grid.adicionarColuna(7, "R$ Real", "valorOperacao");	
		grid.adicionarColuna(8, "R$ Atual", "valor.atual");	
		grid.adicionarColuna(9, "Conta", "conta.nome");	
		
		grid.getModeloColuna().setFormato(5, Formato.MOEDA);		
		grid.getModeloColuna().setFormato(6, Formato.MOEDA);		
		grid.getModeloColuna().setFormato(7, Formato.MOEDA);		
		grid.getModeloColuna().setFormato(8, Formato.MOEDA);		
		
		grid.definirLarguraColunas(50,50,90,120, 150,60,60,60,60,100);
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
