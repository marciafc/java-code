package code.jpa.audit.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import code.jpa.audit.model.Cidade;
import code.jpa.audit.model.Cliente;
import code.jpa.audit.model.Endereco;
import code.jpa.audit.repository.CidadeRepository;
import code.jpa.audit.repository.ClienteRepository;

@Component
public class Processamento {
	@Autowired
	private CidadeRepository cidRepository;
	
	@Autowired
	private ClienteRepository cliRepository;
	
	private static final int IBGE = 2211001;
	
	public void cargaInicial() {
		if(!cidRepository.existsById(IBGE)) {
			Cidade cidade = new Cidade();
			cidade.setId(IBGE);
			cidade.setNome("TERESINA");
			cidRepository.save(cidade);
			
			Cliente cliente = new Cliente();
			cliente.setCpf("123");
			cliente.setNome("GLEYSON SAMPAIO");
			cliente.setCidade(cidade);
			Endereco end = new Endereco();
			end.setCep(7867456);
			end.setLogradouro("AV NOSSA SENHORA");
			cliente.setEndereo(end);
			
			cliRepository.save(cliente);
			
			cliente = new Cliente();
			cliente.setCpf("456");
			cliente.setNome("MARCOS P SILVA");
			cliente.setCidade(cidade);
			end = new Endereco();
			end.setCep(876786);
			end.setLogradouro("RUA DAS JAZIRAS");
			cliente.setEndereo(end);
			
			cliRepository.save(cliente);
			
		}
	}
	public void alteraCidade() {
		System.out.println("----------ALTERANDO CIDADE----------");
		Cidade cidade = new Cidade();
		cidade.setId(IBGE);
		cidade.setNome("TERESINA PIAUI");
		
		cidRepository.save(cidade);
		
	}
	public void alteraCliente() throws Exception {
		System.out.println("----------ALTERANDO CLIENTE----------");
		
		Cliente dbEntity = cliRepository.findByCpf("456"); 
		if(dbEntity!=null) {
			Cliente cli = new Cliente();
			Endereco end = new Endereco();
			end.setCep(dbEntity.getEndereo().getCep());
			end.setCep(988678);
			end.setLogradouro(dbEntity.getEndereo().getLogradouro());
			
			cli.setEndereo(end);
			cli.getEndereo().setLogradouro("RUA JOSE FIRMINO");
			cli.setId(dbEntity.getId());
			cli.setNome("MARCOS PAULO");
			cli.setCpf(dbEntity.getCpf());
			cli.setCidade(dbEntity.getCidade());
			
			cliRepository.save(cli);
			
		}
	}
}
