package digytal.desktop.grid;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import digytal.util.desktop.ss.SSGrade;
import javax.swing.border.TitledBorder;

public class GridClassificacao extends JPanel {
	private SSGrade grid = new SSGrade();
	public GridClassificacao() {
		setBorder(new TitledBorder(null, "Classifica\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout());
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(grid);
		grid.adicionarColuna(0, "Nome", "nome");	
		grid.adicionarColuna(1, "Tipo", "tipoMovimento.nome");	
		grid.definirLarguraColunas(120,70);
		//grid.setSize(400,400);
		add(scroll,BorderLayout.CENTER);
	}
	public void update(Object arrayList) {
		grid.setValue(arrayList);
	}
}
