package digytal.email;

public class Mensagem {
	private String titulo;
	private String remetente;
	private String destinatario;
	private String mensagem;
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
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@Override
	public String toString() {
		return "Mensagem [titulo=" + titulo + ", remetente=" + remetente + ", destinatario=" + destinatario
				+ ", mensagem=" + mensagem + "]";
	}
	
	
}
