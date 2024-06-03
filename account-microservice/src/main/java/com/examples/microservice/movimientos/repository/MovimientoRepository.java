package com.examples.microservice.movimientos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examples.microservice.movimientos.model.Movimiento;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento,Long> {
    
}
