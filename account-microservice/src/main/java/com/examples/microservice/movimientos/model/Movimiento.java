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
    @Column(name = "movimiento_id")
    private Long id;
    @Column(name = "fecha")
    private Date Fecha;
    @Column(name = "tipo_movimiento", length = 50,nullable = false)
    private String tipoMovimiento;
    @Column(name = "valor", nullable = false)
    private double valor;
    @Column(name = "saldo", nullable = false)
    private double saldo;
    // Generate getters and setters
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="cuenta_id", nullable = false)
    private Cuenta cuenta;

    

}
