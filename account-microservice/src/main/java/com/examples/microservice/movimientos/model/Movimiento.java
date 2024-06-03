package com.examples.microservice.movimientos.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date Fecha;
    private String tipoMovimiento;
    private double valor;
    private double saldo;
    // Generate getters and setters
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="cuenta_id", nullable = false)
    private Cuenta cuenta;

    

}
