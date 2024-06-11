package com.examples.microservice.movimientos.controller;

import com.examples.microservice.movimientos.dto.CuentaDto;
import com.examples.microservice.movimientos.model.Cuenta;
import com.examples.microservice.movimientos.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDto> getCuentaById(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.getCuentaById(id);
        return ResponseEntity.ok(cuentaTocuentaDto(cuenta));
    }
    @GetMapping
    public ResponseEntity<List<CuentaDto>> getAllCuentas() {
        List<CuentaDto> result = cuentaService.getAllCuentas().stream().map(this :: cuentaTocuentaDto).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<CuentaDto> createCuenta(@RequestBody CuentaDto cuentaN) {
        Cuenta cuenta = cuentaService.createCuenta( cuentaDtoToCuenta(cuentaN));
        return ResponseEntity.ok(cuentaTocuentaDto(cuenta));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CuentaDto> updateCuenta(@PathVariable Long id, @RequestBody CuentaDto cuentaN) {
        Cuenta cuenta = cuentaService.updateCuenta(id, cuentaDtoToCuenta(cuentaN));
        if(cuenta != null) {
            return ResponseEntity.ok(cuentaTocuentaDto(cuenta));}else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuentaById(@PathVariable Long id) {
        cuentaService.deleteCuentaById(id);
        return ResponseEntity.ok().build();
    }


    private CuentaDto cuentaTocuentaDto(Cuenta cuenta) {
        CuentaDto cuentadto = new CuentaDto();
        cuentadto.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentadto.setTipoCuenta(cuenta.getTipoCuenta());
        cuentadto.setSaldoInicial(cuenta.getSaldoInicial());
        cuentadto.setEstado(cuenta.isEstado());
        cuentadto.setId(cuenta.getId());
        cuentadto.setClienteId(cuenta.getClienteId());
        return cuentadto;
    }
    private Cuenta cuentaDtoToCuenta(CuentaDto cuentaN) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(cuentaN.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaN.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaN.getSaldoInicial());
        cuenta.setEstado(cuentaN.isEstado());
        cuenta.setId(cuentaN.getId());
        cuenta.setClienteId(cuentaN.getClienteId());
        return cuenta;
    }
}
