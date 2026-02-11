package com.netsolucions.netsolucions_api.contract.entity;

import com.netsolucions.netsolucions_api.client.entity.Client;
import com.netsolucions.netsolucions_api.contract.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "contratos")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String identificador; // Ejemplo: "CTR-2026-001"

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @Column(nullable = false)
    private BigDecimal montoMensual;

    //  Guardamos la palabra "ACTIVO" en MySQL en vez de un número.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractStatus estado;

    // Muchos contratos pueden pertenecer a Un Cliente (@ManyToOne).
    // @JoinColumn crea la columna "client_id" automáticamente en nuestra tabla de contratos.
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client cliente;
}

