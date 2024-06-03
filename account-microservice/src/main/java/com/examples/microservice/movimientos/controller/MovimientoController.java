package com.examples.microservice.movimientos.controller;

import com.examples.microservice.movimientos.dto.MovimientoDto;
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

    @PostMapping("cuenta/{cuentaId}")
    public ResponseEntity<MovimientoDto> createMovimiento(@PathVariable  Long cuentaId, @RequestBody MovimientoDto movimientoDto){
        Movimiento movimiento = movimientoService.createMovimiento(cuentaId,movimientoDtoToMovimiento(movimientoDto));
        return new ResponseEntity<>(movimientoToMovimientoDto(movimiento), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDto> getMovimientoById(Long id){
        MovimientoDto movimiento = movimientoToMovimientoDto(  movimientoService.getMovimientoById(id));
        return new ResponseEntity<>(movimiento, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<MovimientoDto>> getAllMovimientos(){
        List<MovimientoDto> result = movimientoService.getAllMovimientos().stream().map(this::movimientoToMovimientoDto).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoDto> updateMovimiento(Long id, MovimientoDto movimientoDto){
        Movimiento movimiento = movimientoService.updateMovimiento(id, movimientoDtoToMovimiento(movimientoDto));
        if(movimiento != null){
            return new ResponseEntity<>(movimientoToMovimientoDto(movimiento), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimientoById(Long id){
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
        return movimiento;
    }

    private MovimientoDto movimientoToMovimientoDto(Movimiento movimiento){
        MovimientoDto movimientoDto = new MovimientoDto();
        movimientoDto.setFecha(movimiento.getFecha());
        movimientoDto.setValor(movimiento.getValor());
        movimientoDto.setTipoMovimiento(movimiento.getTipoMovimiento());
        movimientoDto.setSaldo(movimiento.getSaldo());
        movimientoDto.setId(movimiento.getId());
        return movimientoDto;
    }
}
