package br.com.sancrisxa.cryptography.repository;

import br.com.sancrisxa.cryptography.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
