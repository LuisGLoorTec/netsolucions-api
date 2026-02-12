package com.netsolucions.netsolucions_api.payment.entity;

import com.netsolucions.netsolucions_api.contract.entity.Contract;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double monto; // Cuánto pagó el cliente

    @Column(nullable = false)
    private LocalDate fechaPago; // Cuándo lo pagó

    @Column(nullable = false)
    private String formaPago; // "EFECTIVO", "TRANSFERENCIA", "TARJETA"

    // Muchos pagos -> Un contrato
    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false) // Esta será la Llave Foránea en la base de datos
    private Contract contract;

    @Column(columnDefinition = "boolean default true")
    private boolean activo = true;
}