package com.netsolucions.netsolucions_api.contract.repository;

import com.netsolucions.netsolucions_api.contract.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    // Al heredar de JpaRepository, ya tenemos gratis métodos como:
    // save(), findAll(), findById(), deleteById()

    // Más adelante, si necesitamos buscar contratos por estado o por cliente,
    // lo escribiremos aquí. Por ahora, esto es suficiente.
}