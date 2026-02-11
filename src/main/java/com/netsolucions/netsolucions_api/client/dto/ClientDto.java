package com.netsolucions.netsolucions_api.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // Crea un constructor con todos los datos
@NoArgsConstructor  // Crea un constructor vacío
public class ClientDto {
    private Long id;
    private String nombre;
    private String cedula;
    private String correo;
    private String telefono;
    private String direccion;
    private String estado;
}
