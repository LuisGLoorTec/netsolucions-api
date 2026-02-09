package com.netsolucions.netsolucions_api.client.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data // Lombok: Crea getters, setters y toString automáticamente
@Entity // JPA: Indica que esto es una tabla de base de datos
@Table(name = "clientes")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Obligatorio
    private String nombre;

    @Column(nullable = false, unique = true) // Obligatorio y único
    private String cedula;

    private String correo;
    private String telefono;
    private String direccion;
}
