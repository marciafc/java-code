package digytal.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.dto.Sessao;
import digytal.util.desktop.Formulario;
import digytal.util.desktop.FormularioCadastro;
import digytal.util.desktop.Splash;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Ctx {
	private static ApplicationContext contexto;
	
	private static Sessao sessao;
	
	@Autowired
    public Ctx(ApplicationContext contexto, Sessao sessao) {
		 Ctx.contexto = contexto;
		 Ctx.sessao=sessao;
    }
	public static <T> T  carregar(Class classe, Object registro) {
		Object o =  contexto.getBean(classe);
		if(o instanceof FormularioCadastro) {
			((FormularioCadastro)o).carregar();
			((FormularioCadastro)o).setEntidade(registro);
		}
		return (T) o;
	}
	public static <T> T getBean(Class classe) {
		return (T) contexto.getBean(classe);
	}
	private static Splash splash;
	public static void exibirSplash() {
		splash = new Splash();
		splash.setVisible(true);
	}

	public static void fecharSplash() {
		if (splash != null) {
			splash.setVisible(false);
			splash.dispose();
		}
	}
	public static Sessao getSessao() {
		return sessao;
	}
}


/**
@Component
public class Boo {

    private static Foo foo;

    @Autowired
    public Boo(Foo foo) {
        Boo.foo = foo;
    }

    public static void randomMethod() {
         foo.doStuff();
    }
}
*/

/**

@Component
public class Boo {

    private static Foo foo;
    @Autowired
    private Foo tFoo;

    @PostConstruct
    public void init() {
        Boo.foo = tFoo;
    }

    public static void randomMethod() {
         foo.doStuff();
    }
}
 
 
 */

