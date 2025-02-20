package com.agibank.teste.repository;

import com.agibank.teste.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ClienteRespository extends JpaRepository<ClienteEntity, BigInteger> {

    Optional<ClienteEntity> findById(Long id);

    Optional<ClienteEntity> findByCpf(String cpf);
}
