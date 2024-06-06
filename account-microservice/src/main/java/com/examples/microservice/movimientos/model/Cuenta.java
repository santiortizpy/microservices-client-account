package com.examples.microservice.movimientos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cuenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta_id")
    private Long id;
    @Column(name = "numero_cuenta", nullable = false,unique = true, length = 50)
    private String numeroCuenta;
    @Column(name = "tipo_cuenta", length = 50)
    private String tipoCuenta;
    @Column(name = "saldo_inicial", nullable = false)
    private double saldoInicial;
    @Column(name = "estado", nullable = false)
    private boolean estado;
    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Movimiento> movimientos;


}
