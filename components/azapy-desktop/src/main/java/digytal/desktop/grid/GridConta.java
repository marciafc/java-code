package digytal.desktop.grid;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import digytal.util.Formato;
import digytal.util.desktop.ss.SSGrade;

public class GridConta extends JPanel {
	private SSGrade grid = new SSGrade();
	public GridConta() {
		setBorder(new TitledBorder(null, "Contas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		setLayout(new BorderLayout());
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(grid);
		grid.adicionarColuna(0, "Nome", "nome");	
		grid.adicionarColuna(1, "R$ Saldo", "saldo");	
		grid.getModeloColuna().setFormato(1, Formato.MOEDA);		
		grid.definirLarguraColunas(100,70);
		add(scroll,BorderLayout.CENTER);
	}
	public void update(Object arrayList) {
		grid.setValue(arrayList);
	}
}
