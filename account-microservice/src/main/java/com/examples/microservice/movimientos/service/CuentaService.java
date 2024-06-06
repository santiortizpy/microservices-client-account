package com.examples.microservice.movimientos.service;

import java.util.List;
import java.util.Optional;

import com.examples.microservice.movimientos.dto.ClienteDTO;
import com.examples.microservice.movimientos.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import com.examples.microservice.movimientos.model.Cuenta;
import com.examples.microservice.movimientos.repository.CuentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class CuentaService {
    @Autowired
    CuentaRepository cuentaRepository;

    private final WebClient webClient;

    public CuentaService(WebClient.Builder webClientBuilder) {
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
    // ClienteDTO cliente = getCliente(movimiento.getClientId()).block();
    public Cuenta createCuenta(Cuenta cuenta) {

        return cuentaRepository.save(cuenta); }
    public Cuenta updateCuenta(Long id, Cuenta cuenta) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
        if(cuentaOptional.isPresent()) {
            Cuenta cuentaActualizada = cuentaOptional.get();
            cuentaActualizada.setNumeroCuenta(cuenta.getNumeroCuenta());
            cuentaActualizada.setSaldoInicial(cuenta.getSaldoInicial());
            cuentaActualizada.setEstado(cuenta.isEstado());
            cuentaActualizada.setTipoCuenta(cuenta.getTipoCuenta());
            return cuentaRepository.save(cuentaActualizada);
        }
        return null;
        }
    public void deleteCuentaById(Long id) { cuentaRepository.deleteById(id);}
    public Cuenta getCuentaById(Long id) {
        return cuentaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cuenta no encontrada con Id: "+ id)); }
    public List<Cuenta> getAllCuentas() { return cuentaRepository.findAll(); }
    
}
