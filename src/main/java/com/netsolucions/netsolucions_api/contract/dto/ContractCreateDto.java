package com.netsolucions.netsolucions_api.contract.dto;

import com.netsolucions.netsolucions_api.contract.enums.ContractStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ContractCreateDto {

    @NotBlank(message = "El identificador no puede estar vacío")
    private String identificador;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;

    @NotNull(message = "El monto mensual es obligatorio")
    private BigDecimal montoMensual;

    @NotNull(message = "El estado del contrato es obligatorio")
    private ContractStatus estado;

    // Solo pedimos el id
    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clientId;
}
