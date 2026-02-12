package com.netsolucions.netsolucions_api.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private Long id;
    private Double monto;
    private LocalDate fechaPago;
    private String formaPago;
    private Long contractId;       // Devolvemos el ID del contrato
    private String contractIdentificador;
    private String estado;//Devolvemos también el código legible del contrato (Ej: CTR-2026-001) para que sea más útil.
}