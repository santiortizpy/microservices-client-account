package com.examples.microservice.movimientos.service;

import java.util.List;
import java.util.Optional;

import com.examples.microservice.movimientos.exception.ResourceNotFoundException;
import com.examples.microservice.movimientos.model.Cuenta;
import com.examples.microservice.movimientos.repository.CuentaRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.examples.microservice.movimientos.model.Movimiento;
import com.examples.microservice.movimientos.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

@Service
public class MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    Logger logger = org.slf4j.LoggerFactory.getLogger(MovimientoService.class);




    public Movimiento createMovimiento (Movimiento movimiento){
        if (movimiento.getCuenta() == null) {
            throw new IllegalArgumentException("El movimiento debe tener una cuenta asociada");
        }
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());

                if (cuenta == null) {
                    throw new ResourceNotFoundException("Cuenta no encontrada con número de cuenta: "+ movimiento.getCuenta().getNumeroCuenta());
                }

                List<Movimiento> movimientos = movimientoRepository.findByCuentaId(cuenta.getId());
                movimiento.setSaldo(cuenta.getSaldoInicial());
                movimiento.setFecha(new java.util.Date());
                movimiento.setCuenta(cuenta);
                for(Movimiento m : movimientos){
                    if(m.getTipoMovimiento().trim().equalsIgnoreCase("retiro")){
                        movimiento.setSaldo(movimiento.getSaldo()-m.getValor());
                    }else{
                        movimiento.setSaldo(movimiento.getSaldo()+m.getValor());
                    }
                }
                if(movimiento.getTipoMovimiento().trim().equalsIgnoreCase("retiro")){
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
    public Movimiento getMovimientoById (Long id) {
        logger.info("Buscando movimiento por id: "+id);
        Movimiento movimiento = movimientoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Persona no encontrada con Id: "+ id));
        logger.info("Movimiento encontrado: "+movimiento);
        return movimiento;
    }
    public List<Movimiento> getAllMovimientos () { return movimientoRepository.findAll(); }
}
