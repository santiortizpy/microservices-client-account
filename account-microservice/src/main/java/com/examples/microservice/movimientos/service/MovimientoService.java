package com.examples.microservice.movimientos.service;

import java.util.List;
import java.util.Optional;

import com.examples.microservice.movimientos.dto.ClienteDTO;
import com.examples.microservice.movimientos.exception.ResourceNotFoundException;
import com.examples.microservice.movimientos.model.Cuenta;
import com.examples.microservice.movimientos.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.examples.microservice.movimientos.model.Movimiento;
import com.examples.microservice.movimientos.repository.MovimientoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;




    public Movimiento createMovimiento (String numeroCuenta,Movimiento movimiento){
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta);
                if (cuenta == null) {
                    throw new ResourceNotFoundException("Cuenta no encontrada con Id: "+ numeroCuenta);
                }
                movimiento.setSaldo(cuenta.getSaldoInicial());
                movimiento.setCuenta(cuenta);
                for(Movimiento m : cuenta.getMovimientos()){
                    if(m.getTipoMovimiento().equals("Retiro")){
                        movimiento.setSaldo(movimiento.getSaldo()-m.getValor());
                    }else{
                        movimiento.setSaldo(movimiento.getSaldo()+m.getValor());
                    }
                }
                if(movimiento.getTipoMovimiento().equals("Retiro")){
                    if(movimiento.getSaldo()<movimiento.getValor())throw new ResourceNotFoundException("Saldo no disponible: "+ movimiento.getSaldo());
                    movimiento.setSaldo(movimiento.getSaldo()-movimiento.getValor());
                }else{
                    movimiento.setSaldo(movimiento.getSaldo()+movimiento.getValor());
                }
        return movimientoRepository.save(movimiento);}
    public Movimiento updateMovimiento (Long id,Movimiento movimiento) {
        Optional<Movimiento> movimientoOptional = movimientoRepository.findById(id);
        if(movimientoOptional.isPresent()){
            Movimiento movimientoActualizado = movimientoOptional.get();
            movimientoActualizado.setTipoMovimiento(movimiento.getTipoMovimiento());
            movimientoActualizado.setFecha(movimiento.getFecha());
            movimientoActualizado.setValor(movimiento.getValor());
            movimientoActualizado.setSaldo(movimiento.getSaldo());
            return movimientoRepository.save(movimientoActualizado);
        }
        return null; }
    public void deleteMovimientoById (Long id) { movimientoRepository.deleteById(id); }
    public Movimiento getMovimientoById (Long id) { return movimientoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Persona no encontrada con Id: "+ id)); }
    public List<Movimiento> getAllMovimientos () { return movimientoRepository.findAll(); }
}
