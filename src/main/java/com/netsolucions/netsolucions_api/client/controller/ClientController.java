package com.netsolucions.netsolucions_api.client.controller;

import com.netsolucions.netsolucions_api.client.dto.ClientCreateDto;
import com.netsolucions.netsolucions_api.client.dto.ClientDto;
import com.netsolucions.netsolucions_api.client.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients") // La URL donde nos buscarán en Postman
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // GET: Listar todos los clientes (convertidos a DTO)
    @GetMapping
    public List<ClientDto> listar() {
        return clientService.listar()
                .stream()
                .map(clientService::toDto) // Usamos el convertidor del servicio
                .toList();
    }

    // GET: Buscar un cliente por ID
    @GetMapping("/{id}")
    public ClientDto obtener(@PathVariable Long id) {
        return clientService.toDto(clientService.obtener(id));
    }

    // POST: Crear un nuevo cliente (Validando que el DTO venga correcto)
    @PostMapping
    public ClientDto crear(@Valid @RequestBody ClientCreateDto dto) {
        return clientService.toDto(clientService.crearDesdeDto(dto));
    }
}