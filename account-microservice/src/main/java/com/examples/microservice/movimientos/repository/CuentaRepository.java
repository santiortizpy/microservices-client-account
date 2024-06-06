package com.examples.microservice.movimientos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examples.microservice.movimientos.model.Cuenta;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta,Long> {
    Cuenta findByNumeroCuenta(String numeroCuenta);
}
