package com.netsolucions.netsolucions_api.payment.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PaymentCreateDto {
    private Double monto;
    private LocalDate fechaPago;
    private String formaPago; // Ejemplo: "EFECTIVO", "TRANSFERENCIA"

    // IMPORTANTE: Solo pedimos el ID del contrato, no el objeto entero.
    private Long contractId;
}