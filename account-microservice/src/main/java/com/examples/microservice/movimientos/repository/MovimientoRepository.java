package com.examples.microservice.movimientos.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.examples.microservice.movimientos.model.Movimiento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento,Long> {


    /**
     * Encuentra los movimientos por cliente y rango de fechas.
     *
     * @param clienteId el ID del cliente
     * @param fechaInicio la fecha de inicio del rango
     * @param fechaFin la fecha de fin del rango
     * @return una lista de movimientos
     */
@Query("SELECT m FROM Movimiento m WHERE m.cuenta.clienteId = :clienteId AND m.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Movimiento> findMovimientosByClienteAndFecha(@Param("clienteId") Long clienteId, @Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);

    /**
     * Encuentra los movimientos por cliente y rango de fechas.
     *
     * @param clienteId el ID del cliente
     * @param fechaInicio la fecha de inicio del rango
     * @param fechaFin la fecha de fin del rango
     * @return una lista de movimientos
     */
    List<Movimiento> findByCuentaId(Long id);
}
