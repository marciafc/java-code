package code.jpa.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import code.jpa.audit.model.LogDatabase;

public interface LogDatabaseRepository extends JpaRepository<LogDatabase, Integer> {

}
