package com.examples.microservice.movimientos.controller;

import com.examples.microservice.movimientos.dto.ReporteDTO;
import com.examples.microservice.movimientos.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public List<ReporteDTO> obtenerReportes(
            @RequestParam("fechaInicio") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam("cliente") Long clienteId){

        return reporteService.obtenerReportes(fechaInicio, fechaFin, clienteId);

    }

}
