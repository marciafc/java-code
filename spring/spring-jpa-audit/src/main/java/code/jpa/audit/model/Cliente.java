package code.jpa.audit.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import code.jpa.audit.infra.Auditable;

@Entity
public class Cliente implements Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nome;
	private String cpf;
	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;
	@Embedded
	private Endereco endereo;
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Endereco getEndereo() {
		return endereo;
	}
	public void setEndereo(Endereco endereo) {
		this.endereo = endereo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", cidade=" + cidade + ", endereo=" + endereo
				+ "]";
	}
	
}
