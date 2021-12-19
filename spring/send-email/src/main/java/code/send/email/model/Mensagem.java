package code.send.email.model;

public class Mensagem {
	private String tema;
	private String titulo;
	private String corpo;
	private String remetente;
	private String destinatario;
	public String getRemetente() {
		return remetente;
	}
	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getCorpo() {
		return corpo;
	}
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	@Override
	public String toString() {
		return "Mensagem [tema=" + tema + ", titulo=" + titulo + ", corpo=" + corpo + ", remetente=" + remetente
				+ ", destinatario=" + destinatario + "]";
	}
	
	
	
}
