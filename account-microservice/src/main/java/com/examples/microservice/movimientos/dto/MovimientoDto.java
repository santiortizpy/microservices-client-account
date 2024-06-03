package com.examples.microservice.movimientos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDto {

    private Long id;
    private Date Fecha;
    private String tipoMovimiento;
    private double valor;
    private double saldo;
}
