package com.netsolucions.netsolucions_api.contract.dto;

import com.netsolucions.netsolucions_api.contract.enums.ContractStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ContractDto {
    private Long id;
    private String identificador;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal montoMensual;
    private ContractStatus estado;
    // Devolvemos el ID del cliente para saber a quién pertenece
    private Long clientId;
}
