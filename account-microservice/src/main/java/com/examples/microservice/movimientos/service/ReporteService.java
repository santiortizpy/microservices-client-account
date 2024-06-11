package com.examples.microservice.movimientos.service;

import com.examples.microservice.movimientos.dto.ClienteDTO;
import com.examples.microservice.movimientos.dto.ReporteDTO;
import com.examples.microservice.movimientos.model.Cuenta;
import com.examples.microservice.movimientos.model.Movimiento;
import com.examples.microservice.movimientos.repository.CuentaRepository;
import com.examples.microservice.movimientos.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    private final WebClient webClient;

    public ReporteService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/clientes").build();
    }

    public Mono<ClienteDTO> getCliente(Long clienteId){
        return webClient.get()
                .uri("/{clienteId}",clienteId)
                .retrieve()
                .bodyToMono(ClienteDTO.class)
                .onErrorResume(WebClientResponseException.NotFound.class, e -> Mono.empty())
                .onErrorResume(WebClientResponseException.class, e -> Mono.empty());
    }
    public List<ReporteDTO> obtenerReportes(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId) {
        List<Movimiento> movimientos = movimientoRepository.findMovimientosByClienteAndFecha(clienteId, fechaInicio.atStartOfDay(), fechaFin.atStartOfDay());
        //List<Cuenta> cuentas = cuentaRepository.findByClientId(clienteId);
        ClienteDTO cliente = getCliente(clienteId).block();
        List<ReporteDTO> reportes = new ArrayList<ReporteDTO>();
        for (Movimiento movimiento : movimientos) {
           /* Cuenta  cuenta = cuentas.stream()
                    .filter(c -> c.getId().equals(movmimiento.getCuenta().getId()))
                    .findFirst()
                    .orElse(null);*/
            ReporteDTO reporte = new ReporteDTO();
            reporte.setFecha(movimiento.getFecha());
            reporte.setCliente(cliente.getNombre());
            reporte.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
            reporte.setTipoCuenta(movimiento.getCuenta().getTipoCuenta());
            reporte.setSaldoInicial(movimiento.getCuenta().getSaldoInicial());
            reporte.setMovimiento(movimiento.getValor());
            reporte.setSaldoDisponible(movimiento.getSaldo());
            reportes.add(reporte);
        }
        return reportes;
    }
}
