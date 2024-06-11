package com.examples.microservice.movimientos.controller;

import com.examples.microservice.movimientos.dto.CuentaDto;
import com.examples.microservice.movimientos.dto.MovimientoDto;
import com.examples.microservice.movimientos.model.Cuenta;
import com.examples.microservice.movimientos.model.Movimiento;
import com.examples.microservice.movimientos.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @PostMapping()
    public ResponseEntity<MovimientoDto> createMovimiento(@RequestBody MovimientoDto movimientoDto){
        Movimiento movimiento = movimientoService.createMovimiento(movimientoDtoToMovimiento(movimientoDto));
        return new ResponseEntity<>(movimientoToMovimientoDto(movimiento), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDto> getMovimientoById(@PathVariable Long id){
        MovimientoDto movimiento = movimientoToMovimientoDto(  movimientoService.getMovimientoById(id));
        return new ResponseEntity<>(movimiento, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<MovimientoDto>> getAllMovimientos(){
        List<MovimientoDto> result = movimientoService.getAllMovimientos().stream().map(this::movimientoToMovimientoDto).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoDto> updateMovimiento(@PathVariable Long id, @RequestBody MovimientoDto movimientoDto){
        Movimiento movimiento = movimientoService.updateMovimiento(id, movimientoDtoToMovimiento(movimientoDto));
        if(movimiento != null){
            return new ResponseEntity<>(movimientoToMovimientoDto(movimiento), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimientoById(@PathVariable Long id){
        movimientoService.deleteMovimientoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private  Movimiento movimientoDtoToMovimiento (MovimientoDto movimientoDto){
        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(movimientoDto.getFecha());
        movimiento.setValor(movimientoDto.getValor());
        movimiento.setTipoMovimiento(movimientoDto.getTipoMovimiento());
        movimiento.setSaldo(movimientoDto.getSaldo());
        movimiento.setId(movimientoDto.getId());
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta(movimiento.getCuenta().getTipoCuenta());
        cuenta.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
        cuenta.setSaldoInicial(movimiento.getCuenta().getSaldoInicial());
        cuenta.setEstado(movimiento.getCuenta().isEstado());
        cuenta.setId(movimiento.getCuenta().getId());
        cuenta.setClienteId(movimiento.getCuenta().getClienteId());
        movimiento.setCuenta(cuenta);
        return movimiento;
    }

    private MovimientoDto movimientoToMovimientoDto(Movimiento movimiento){
        MovimientoDto movimientoDto = new MovimientoDto();
        movimientoDto.setFecha(movimiento.getFecha());
        movimientoDto.setValor(movimiento.getValor());
        movimientoDto.setTipoMovimiento(movimiento.getTipoMovimiento());
        movimientoDto.setSaldo(movimiento.getSaldo());
        movimientoDto.setId(movimiento.getId());
        CuentaDto cuenta = new CuentaDto();
        cuenta.setTipoCuenta(movimiento.getCuenta().getTipoCuenta());
        cuenta.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
        cuenta.setSaldoInicial(movimiento.getCuenta().getSaldoInicial());
        cuenta.setEstado(movimiento.getCuenta().isEstado());
        cuenta.setId(movimiento.getCuenta().getId());
        cuenta.setClienteId(movimiento.getCuenta().getClienteId());
        movimientoDto.setCuenta(cuenta);
        return movimientoDto;
    }
}
