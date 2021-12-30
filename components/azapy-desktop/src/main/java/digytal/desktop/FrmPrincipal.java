package digytal.desktop;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.beans.Ctx;
import digytal.desktop.cadastro.FrmClassificacao;
import digytal.desktop.cadastro.FrmConta;
import digytal.desktop.grid.GridClassificacaoTree;
import digytal.desktop.grid.GridConta;
import digytal.desktop.grid.GridLancamento;
import digytal.desktop.grid.GridPagamento;
import digytal.desktop.lancamento.FrmLancamento;
import digytal.desktop.lancamento.FrmPagamento;
import digytal.dto.Classificacao;
import digytal.dto.Conta;
import digytal.dto.Lancamento;
import digytal.dto.Pagamento;
import digytal.dto.view.Classificacoes;
import digytal.dto.view.Lancamentos;
import digytal.entity.embedded.LancamentoValor;
import digytal.service.ClassificacaoService;
import digytal.service.ContaService;
import digytal.service.LancamentoService;
import digytal.service.PagamentoService;
import digytal.sql.Search;
import digytal.util.desktop.Formulario;
import digytal.util.desktop.ss.SSBotao;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmPrincipal extends Formulario {
	private SSBotao bRefresh = new SSBotao();
	private SSBotao bClassificacao = new SSBotao();
	private SSBotao bConta = new SSBotao();
	private SSBotao bLancamento = new SSBotao();
	private GridConta gridContas = new GridConta();
	private GridClassificacaoTree gridClassificacao = new GridClassificacaoTree();
	private GridLancamento gridLancamento = new GridLancamento();
	private GridPagamento gridPagamento = new GridPagamento();
	
	
	@Autowired
	private ContaService contaService;
	@Autowired
	private ClassificacaoService classificacaoService;
	@Autowired
	private LancamentoService lancamentoService;
	@Autowired
	private PagamentoService pagamentoService;
	
	public FrmPrincipal() {
		super(new BorderLayout());
		botoes();
		eventos();
		setTitulo("CFIP - Gestão Financeira");
		setDescricao("Controle Financeiro Pessoal");
	}
	private void botoes() {
		bConta.setText("Conta");
		bConta.setIcone("card");
		bRefresh.setText("Refresh");
		bRefresh.setIcone("atualizar");
		bLancamento.setText("Lançamento");
		bLancamento.setIcone("data_add");
		bClassificacao.setText("Classificação");
		bClassificacao.setIcone("tag_blue");
		
		JToolBar pBotoes = new JToolBar(null, JToolBar.VERTICAL);
		
		pBotoes.add(bConta);
		pBotoes.add(bClassificacao);
		pBotoes.add(bLancamento);
		pBotoes.add(bRefresh);
		getConteudo().add(pBotoes,BorderLayout.WEST);
		
		JPanel pGrids = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc_gridContas = new GridBagConstraints();
		gbc_gridContas.weighty = 0.5;
		gbc_gridContas.gridwidth = 1;
		gbc_gridContas.weightx = 0.4;
		gbc_gridContas.anchor = GridBagConstraints.NORTHWEST;
		gbc_gridContas.fill = GridBagConstraints.BOTH;
		gbc_gridContas.gridx = 0;
		gbc_gridContas.gridy = 0;
		pGrids.add(gridContas, gbc_gridContas);
		
		GridBagConstraints gbc_gridClassificacao = new GridBagConstraints();
		gbc_gridClassificacao.weighty = 4.0;
		gbc_gridClassificacao.gridwidth = 1;
		gbc_gridClassificacao.anchor = GridBagConstraints.NORTHWEST;
		gbc_gridClassificacao.fill = GridBagConstraints.BOTH;
		gbc_gridClassificacao.insets = new Insets(3, 0, 0, 0);
		gbc_gridClassificacao.gridx = 0;
		gbc_gridClassificacao.gridy = 1;
		
		pGrids.add(gridClassificacao, gbc_gridClassificacao);
		
		GridBagConstraints gbc_gridLancamento = new GridBagConstraints();
		gbc_gridLancamento.weightx = 2.0;
		gbc_gridLancamento.weighty = 1.0;
		gbc_gridLancamento.anchor = GridBagConstraints.NORTHWEST;
		gbc_gridLancamento.gridwidth = 1;
		gbc_gridLancamento.fill = GridBagConstraints.BOTH;
		gbc_gridLancamento.gridx = 1;
		gbc_gridLancamento.gridy = 0;
		pGrids.add(gridLancamento, gbc_gridLancamento);

		GridBagConstraints gbc_gridPagamento = new GridBagConstraints();
		gbc_gridPagamento.gridwidth = 1;
		gbc_gridPagamento.anchor = GridBagConstraints.NORTHWEST;
		gbc_gridPagamento.fill = GridBagConstraints.BOTH;
		gbc_gridPagamento.insets = new Insets(3, 0, 0, 0);
		gbc_gridPagamento.gridx = 1;
		gbc_gridPagamento.gridy = 1;
		pGrids.add(gridPagamento, gbc_gridPagamento);

		getConteudo().add(pGrids,BorderLayout.CENTER);
		setSize(1180,740);
		//setBounds(new Rectangle(1180, 740));
		
	}
	private void eventos() {
		bConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmConta frm = Ctx.carregar(FrmConta.class,new Conta());
				exibir(frm);
			}
		});
		bLancamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmLancamento frm = Ctx.carregar(FrmLancamento.class,new Lancamento());
				exibir(frm);
			}
		});
		bClassificacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmClassificacao frm = Ctx.carregar(FrmClassificacao.class,new Classificacao());
				exibir(frm);
			}
		});
		bRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		gridLancamento.getGrid().addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {
	            	iniciarPagamento();
	            }
	         }
	      });
		
	}
	public void load() {
		pagamentoService.atualizar();
		loadContas();
		loadClassificacao();
		loadLancamentos();
		loadPagamentos();
	}
	private void loadContas() {
		List<Conta> result = contaService.search();
		gridContas.update(result);
	}
	
	private void loadClassificacao() {
		List<Classificacoes> result = classificacaoService.arvore();	
		gridClassificacao.update(result);
	}
	private void loadLancamentos() {
		try {
			Map<String, Object> filter = new HashMap<>();
			filter.put("status.quitado", false);
			filter.put("orderBy", "parcelamento.proximoVencimento.data DESC");
			gridLancamento.update(lancamentoService.search(Search.of(filter)));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void loadPagamentos() {
		try {
			Map<String, Object> filter = new HashMap<>();
			filter.put("orderBy", "registro.data DESC, id");
			gridPagamento.update(lancamentoService.searchPagamentos(Search.of(filter)));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void iniciarPagamento() {
		Lancamentos lancto = (Lancamentos) gridLancamento.getGrid().getSelectedRowData();
		LancamentoValor lanctoValor = lancto.getValor();
		Pagamento pgto = new Pagamento();
		pgto.lancamento=lancto.getId();
		pgto.descricao = lancto.getDescricao();// +"-" +lancto.getParcelamento().getProximaParcela();  
		
		Double valor = lancto.getParcelamento().getValorParcela();
		if(valor > lancto.getValor().getAtual())
			valor = lancto.getValor().getAtual();
		
		pgto.valorParcela=valor;
		pgto.valorPendente = lanctoValor.getPendente();
		pgto.valorAcrescimo = lanctoValor.getAcrescimo();
		pgto.valorDesconto = lanctoValor.getDesconto();
		pgto.dataPagamento = lancto.getParcelamento().getProximoVencimento().getData();
		pgto.tipoMovimento = lancto.getClassificacao().getTipoMovimento();
		FrmPagamento frm = Ctx.carregar(FrmPagamento.class, pgto );
		exibir(frm);
	}
	
}
