package digytal.desktop.grid;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;

import digytal.dto.view.Classificacoes;
import digytal.util.desktop.ss.SSGrade;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class GridClassificacaoTree extends JPanel {
	private DefaultMutableTreeNode main=new DefaultMutableTreeNode("Plano de Contas");  
	private JTree tree = new JTree(main);
	public GridClassificacaoTree() {
		setBorder(new TitledBorder(null, "Classifica\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout());
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(tree);
		add(scroll,BorderLayout.CENTER);
		
		tree.setBackground(UIManager.getColor("Button.background"));
		
	}
	public void update(List<Classificacoes> result) {
		for(Classificacoes c:result) {
			DefaultMutableTreeNode d = new DefaultMutableTreeNode(c.getDescricao());
			rec(d, c);
			main.add(d);
		}
		TreePath path = new TreePath(main.getPath());
		tree.expandPath(path);
	}
	private void rec(DefaultMutableTreeNode d,Classificacoes c) {
		for(Classificacoes cc: c.getFilhos()) {
			DefaultMutableTreeNode dd = new DefaultMutableTreeNode(cc.getDescricao());
			d.add(dd);
			rec(dd,cc);
			if(cc.getFilhos().size()==0)
				continue;
		}
	}
}
