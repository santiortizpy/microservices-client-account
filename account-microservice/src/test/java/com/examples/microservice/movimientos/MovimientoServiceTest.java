package com.examples.microservice.movimientos;

import com.examples.microservice.movimientos.exception.ResourceNotFoundException;
import com.examples.microservice.movimientos.model.Cuenta;
import com.examples.microservice.movimientos.model.Movimiento;
import com.examples.microservice.movimientos.repository.CuentaRepository;
import com.examples.microservice.movimientos.repository.MovimientoRepository;
import com.examples.microservice.movimientos.service.MovimientoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MovimientoServiceTest {

    @Mock
    private MovimientoRepository movimientoRepository;

    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private MovimientoService movimientoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createMovimientoWithValidData() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("123456");
        cuenta.setSaldoInicial(1000.0);
        cuenta.setClienteId(1L);
        cuenta.setTipoCuenta("AHORRO");
        cuenta.setEstado(true);
        Movimiento movimiento = new Movimiento();
        movimiento.setTipoMovimiento("Deposito");
        movimiento.setValor(100.0);
        movimiento.setCuenta(cuenta);

        when(cuentaRepository.findByNumeroCuenta("123456")).thenReturn(cuenta);
        when(movimientoRepository.save(any(Movimiento.class))).thenReturn(movimiento);

        Movimiento result = movimientoService.createMovimiento(movimiento);

        assertEquals(100.0, result.getValor());
        assertEquals("Deposito", result.getTipoMovimiento());
    }

    @Test
    public void createMovimientoWithInvalidCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("123456");
        cuenta.setSaldoInicial(1000.0);
        cuenta.setClienteId(1L);
        cuenta.setTipoCuenta("AHORRO");
        cuenta.setEstado(true);

        Movimiento movimiento = new Movimiento();
        movimiento.setTipoMovimiento("Deposito");
        movimiento.setValor(100.0);
        movimiento.setCuenta(cuenta);

        when(cuentaRepository.findByNumeroCuenta("123456")).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> movimientoService.createMovimiento( movimiento));
    }
    @Test
    public void createMovimientoWithoutCuenta() {

        Movimiento movimiento = new Movimiento();
        movimiento.setTipoMovimiento("Deposito");
        movimiento.setValor(100.0);
        movimiento.setCuenta(null);



        assertThrows(IllegalArgumentException.class, () -> movimientoService.createMovimiento( movimiento));
    }

    @Test
    public void createMovimientoWithvalidSaldo() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("123456");
        cuenta.setSaldoInicial(100.0);
        cuenta.setClienteId(1L);
        cuenta.setTipoCuenta("AHORRO");
        cuenta.setEstado(true);

        Movimiento movimiento = new Movimiento();
        movimiento.setTipoMovimiento("RETIRO");
        movimiento.setValor(100.0);
        movimiento.setCuenta(cuenta);

        when(cuentaRepository.findByNumeroCuenta("123456")).thenReturn(cuenta);
        when(movimientoRepository.save(any(Movimiento.class))).thenReturn(movimiento);
        Movimiento result = movimientoService.createMovimiento(movimiento);
        assertEquals(0.0, result.getSaldo());
        assertEquals("RETIRO", result.getTipoMovimiento());
    }

    @Test
    public void createMovimientoWithInvalidSaldo() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("123456");
        cuenta.setSaldoInicial(0.0);
        cuenta.setClienteId(1L);
        cuenta.setTipoCuenta("AHORRO");
        cuenta.setEstado(true);

        Movimiento movimiento = new Movimiento();
        movimiento.setTipoMovimiento("RETIRO");
        movimiento.setValor(100.0);
        movimiento.setCuenta(cuenta);

        when(cuentaRepository.findByNumeroCuenta("123456")).thenReturn(cuenta);

        assertThrows(ResourceNotFoundException.class, () -> movimientoService.createMovimiento( movimiento));
    }


    @Test
    public void updateMovimientoWithValidData() {
        Movimiento movimiento = new Movimiento();
        movimiento.setId(1L);
        movimiento.setTipoMovimiento("Deposito");
        movimiento.setValor(100.0);

        when(movimientoRepository.findById(1L)).thenReturn(Optional.of(movimiento));
        when(movimientoRepository.save(any(Movimiento.class))).thenReturn(movimiento);

        Movimiento result = movimientoService.updateMovimiento(1L, movimiento);

        assertEquals(100.0, result.getValor());
        assertEquals("Deposito", result.getTipoMovimiento());
    }

    @Test
    public void updateMovimientoWithInvalidId() {
        Movimiento movimiento = new Movimiento();
        movimiento.setId(1L);
        movimiento.setTipoMovimiento("Deposito");
        movimiento.setValor(100.0);

        when(movimientoRepository.findById(1L)).thenReturn(Optional.empty());

        assertNull(movimientoService.updateMovimiento(1L, movimiento));
    }

    @Test
    public void getMovimientoByIdWithValidId() {
        Movimiento movimiento = new Movimiento();
        movimiento.setId(1L);
        movimiento.setTipoMovimiento("Deposito");
        movimiento.setValor(100.0);

        when(movimientoRepository.findById(1L)).thenReturn(Optional.of(movimiento));

        Movimiento result = movimientoService.getMovimientoById(1L);

        assertEquals(100.0, result.getValor());
        assertEquals("Deposito", result.getTipoMovimiento());
    }

    @Test
    public void getMovimientoByIdWithInvalidId() {
        when(movimientoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> movimientoService.getMovimientoById(1L));
    }
}