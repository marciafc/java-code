package code.jasper.reports;

public class Integrante {
	private String nome;
	private String github;
	private String linkedin;

	public Integrante(String nome, String github, String linkedin) {
		super();
		this.nome = nome;
		this.github = github;
		this.linkedin = linkedin;
	}
	public String getNome() {
		return nome;
	}
	public String getGithub() {
		return github;
	}
	public String getLinkedin() {
		return linkedin;
	}
	
}
