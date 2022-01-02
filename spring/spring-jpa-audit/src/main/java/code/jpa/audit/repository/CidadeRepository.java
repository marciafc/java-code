package code.jpa.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import code.jpa.audit.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
