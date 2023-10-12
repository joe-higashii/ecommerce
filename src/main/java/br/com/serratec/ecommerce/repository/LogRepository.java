package br.com.serratec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serratec.ecommerce.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

}
