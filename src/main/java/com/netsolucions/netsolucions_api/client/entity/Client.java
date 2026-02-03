package com.netsolucions.netsolucions_api.client.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data // Lombok: Nos ahorra escribir getters y setters
@Entity // JPA: Convierte esta clase en una Tabla
@Table(name = "clients") // Nombre real de la tabla en MySQL
public class Client {

    @Id // Llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremental (1, 2, 3...)
    private Long id;

    @Column(nullable = false) // No puede dejarse vacío
    private String name;

    @Column(nullable = false, unique = true) // No puede haber dos clientes con la misma cédula
    private String dni;

    private String email;
    private String phone;
    private String address;
}