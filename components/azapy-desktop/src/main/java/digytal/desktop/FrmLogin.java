package digytal.desktop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.beans.Ctx;
import digytal.dto.Sessao;
import digytal.service.ClassificacaoService;
import digytal.service.LancamentoService;
import digytal.util.Imagens;
import digytal.util.desktop.ss.SSBotao;
import digytal.util.desktop.ss.SSCabecalho;
import digytal.util.desktop.ss.SSCaixaCombinacao;
import digytal.util.desktop.ss.SSCampoSenha;
import digytal.util.desktop.ss.SSCampoTexto;
import digytal.util.desktop.ss.SSMensagem;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmLogin extends JFrame {
    private JPanel pnlLogin = new JPanel();
    private SSBotao btOk = new SSBotao();
    private SSBotao btSair = new SSBotao();
    private SSCampoTexto cLogin = new SSCampoTexto();
    private SSCampoSenha cSenha = new SSCampoSenha();
    private SSCaixaCombinacao cEmpresas = new SSCaixaCombinacao();
    
    @Autowired
    private LancamentoService s;
    public FrmLogin() {
    	this.setIconImage(Imagens.pngImage("app"));
    	setTitle("LOGIN");
    	cEmpresas.setRotulo("Empresa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(259, 270));
        setLocationRelativeTo(null);

        pnlLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnlLogin.setLayout(new BorderLayout(0, 0));
        setContentPane(pnlLogin);

        SSCabecalho cabecalho = new SSCabecalho();
        cabecalho.setTitulo("LOGIN");
        cabecalho.setDescricao("Acesse o sistema");

        pnlLogin.add(cabecalho, BorderLayout.NORTH);

        JPanel conteudo = new JPanel();
        conteudo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        pnlLogin.add(conteudo, BorderLayout.CENTER);
        GridBagLayout gbl_conteudo = new GridBagLayout();
        conteudo.setLayout(gbl_conteudo);
        cLogin.setColunas(10);

        cLogin.setRotulo("Usuário");
        GridBagConstraints gbc_txtUsuario = new GridBagConstraints();
        gbc_txtUsuario.weightx = 1.0;
        gbc_txtUsuario.anchor = GridBagConstraints.NORTHWEST;
        gbc_txtUsuario.insets = new Insets(5, 5, 0, 5);
        gbc_txtUsuario.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtUsuario.gridx = 0;
        gbc_txtUsuario.gridy = 0;
        conteudo.add(cLogin, gbc_txtUsuario);

        cSenha.setRotulo("Senha");
        GridBagConstraints gbc_txtSenha = new GridBagConstraints();
        gbc_txtSenha.weightx = 1.0;
        gbc_txtSenha.anchor = GridBagConstraints.NORTHEAST;
        gbc_txtSenha.insets = new Insets(5, 5, 0, 5);
        gbc_txtSenha.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtSenha.gridx = 0;
        gbc_txtSenha.gridy = 1;
        conteudo.add(cSenha, gbc_txtSenha);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        pnlLogin.add(panel_1, BorderLayout.SOUTH);

        btOk.setText("LOGIN");
        btOk.setIcone("chave");

        panel_1.add(btOk);

        btSair.setText("SAIR");
        btSair.setIcone("fechar");
        panel_1.add(btSair);
        cLogin.setTudoMaiusculo(false);
        cSenha.setTudoMaiusculo(false);
        cLogin.setText("");
        cSenha.setText("");
        
        GridBagConstraints gbc_ce = new GridBagConstraints();
        gbc_ce.weighty = 1.0;
        gbc_ce.anchor = GridBagConstraints.NORTHEAST;
        gbc_ce.insets = new Insets(5, 5, 5, 5);
        gbc_ce.fill = GridBagConstraints.HORIZONTAL;
        gbc_ce.gridx = 0;
        gbc_ce.gridy = 2;
        conteudo.add(cEmpresas, gbc_ce);

        btSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               fechar();
            }
        });
        
        btOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               login();
            }
        });
        
        cEmpresas.setEnabled(false);
        cEmpresas.setPrimeiroElementoVazio(true);
    }
    private void login() {
		try {
			String login = cLogin.getText();
			String senha = cSenha.getText();
			
			if(login.equals("a") && senha.equals("a")) {
				Sessao sessao = new Sessao();
				iniciar(sessao);
				this.dispose();
			}else {
				SSMensagem.avisa("Usuário ou senha inválida");
			}
			
			
		} catch (Exception e) {
			SSMensagem.erro(e.getMessage());
			e.printStackTrace();
		}
	}
    private void exibirEmpresas() {
    	cEmpresas.limparItens();
    	//cEmpresas.setItens(empresas,"nomeFantasia");
    	//cEmpresas.setEnabled(true);
    	
    }
    @Value("${empresa}")
    private String empresa;
    private void iniciar(Sessao sessao) {
    	MDIPrincipal mdi = Ctx.getBean(MDIPrincipal.class);
    	mdi.setEmpresa(empresa);
    	mdi.iniciar();
    	mdi.setVisible(true);
    }
    public void exibir() {
        this.setVisible(true);
        Ctx.fecharSplash();
    }

    public String getLogin() {
        return cLogin.getText();
    }

    public String getSenha() {
        return cSenha.getText();
    }

    
    private void fechar() {
        System.exit(0);
    }
}
