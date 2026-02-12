package com.netsolucions.netsolucions_api.payment.service;

import com.netsolucions.netsolucions_api.contract.entity.Contract;
import com.netsolucions.netsolucions_api.contract.repository.ContractRepository;
import com.netsolucions.netsolucions_api.payment.dto.PaymentCreateDto;
import com.netsolucions.netsolucions_api.payment.dto.PaymentDto;
import com.netsolucions.netsolucions_api.payment.entity.Payment;
import com.netsolucions.netsolucions_api.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ContractRepository contractRepository;

    public PaymentService(PaymentRepository paymentRepository, ContractRepository contractRepository) {
        this.paymentRepository = paymentRepository;
        this.contractRepository = contractRepository;
    }

    //CREAR (POST)
    public PaymentDto crear(PaymentCreateDto dto) {
        Contract contract = contractRepository.findById(dto.getContractId())
                .orElseThrow(() -> new RuntimeException("Error: El contrato no existe"));

        Payment payment = new Payment();
        payment.setMonto(dto.getMonto());
        payment.setFechaPago(dto.getFechaPago());
        payment.setFormaPago(dto.getFormaPago());
        payment.setContract(contract);
        payment.setActivo(true);

        return toDto(paymentRepository.save(payment));
    }

    //LEER TODOS (GET)
    public List<PaymentDto> listarTodos() {
        return paymentRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    //LEER POR ID (GET /{id})
    public PaymentDto obtenerPorId(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Pago no encontrado"));
        return toDto(payment);
    }

    //LEER POR CONTRATO (GET /contract/{id})
    public List<PaymentDto> listarPorContrato(Long contractId) {
        return paymentRepository.findByContractId(contractId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    //ACTUALIZAR (PUT)
    public PaymentDto actualizar(Long id, PaymentCreateDto dto) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Pago no encontrado"));
        Contract contract = contractRepository.findById(dto.getContractId())
                .orElseThrow(() -> new RuntimeException("Error: El contrato destino no existe"));

        payment.setMonto(dto.getMonto());
        payment.setFechaPago(dto.getFechaPago());
        payment.setFormaPago(dto.getFormaPago());
        payment.setContract(contract);

        return toDto(paymentRepository.save(payment));
    }

    //BORRADO LÓGICO (DELETE)
    public void eliminar(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Pago no encontrado"));
        payment.setActivo(false);
        paymentRepository.save(payment);
    }

    //RESTAURAR (PATCH)
    public PaymentDto restaurar(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Pago no encontrado"));
        payment.setActivo(true);
        return toDto(paymentRepository.save(payment));
    }

    private PaymentDto toDto(Payment entity) {
        String estadoTexto = entity.isActivo() ? "VALIDO" : "ANULADO";

        return new PaymentDto(
                entity.getId(),
                entity.getMonto(),
                entity.getFechaPago(),
                entity.getFormaPago(),
                entity.getContract().getId(),
                entity.getContract().getIdentificador(),
                estadoTexto
        );
    }
}