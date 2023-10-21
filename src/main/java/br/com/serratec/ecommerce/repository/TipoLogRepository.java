package br.com.serratec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serratec.ecommerce.model.TipoLog;

@Repository
public interface TipoLogRepository extends JpaRepository<TipoLog, Long> {

}
