package com.netsolucions.netsolucions_api.payment.controller;

import com.netsolucions.netsolucions_api.payment.dto.PaymentCreateDto;
import com.netsolucions.netsolucions_api.payment.dto.PaymentDto;
import com.netsolucions.netsolucions_api.payment.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    //CREAR
    @PostMapping
    public ResponseEntity<PaymentDto> crear(@RequestBody PaymentCreateDto dto) {
        return new ResponseEntity<>(paymentService.crear(dto), HttpStatus.CREATED);
    }

    //LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<PaymentDto>> listarTodos() {
        return new ResponseEntity<>(paymentService.listarTodos(), HttpStatus.OK);
    }

    //OBTENER POR ID
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> obtenerPorId(@PathVariable Long id) {
        return new ResponseEntity<>(paymentService.obtenerPorId(id), HttpStatus.OK);
    }

    //LISTAR POR CONTRATO (Historial)
    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<PaymentDto>> listarPorContrato(@PathVariable Long contractId) {
        return new ResponseEntity<>(paymentService.listarPorContrato(contractId), HttpStatus.OK);
    }

    //ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> actualizar(@PathVariable Long id, @RequestBody PaymentCreateDto dto) {
        return new ResponseEntity<>(paymentService.actualizar(id, dto), HttpStatus.OK);
    }

    //ELIMINAR (Anular)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        paymentService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //RESTAURAR
    @PatchMapping("/{id}/restore")
    public ResponseEntity<PaymentDto> restaurar(@PathVariable Long id) {
        return new ResponseEntity<>(paymentService.restaurar(id), HttpStatus.OK);
    }
}