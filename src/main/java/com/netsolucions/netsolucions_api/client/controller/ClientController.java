package com.netsolucions.netsolucions_api.client.controller;

import com.netsolucions.netsolucions_api.client.dto.ClientCreateDto;
import com.netsolucions.netsolucions_api.client.dto.ClientDto;
import com.netsolucions.netsolucions_api.client.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    // ENDPOINT PARA ACTUALIZAR (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @Valid @RequestBody ClientCreateDto updateDto) {
        ClientDto updatedClient = clientService.updateClient(id, updateDto);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    // ENDPOINT PARA BORRADO LÓGICO (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content (Éxito, pero sin datos de retorno)
    }
    // ENDPOINT PARA RESTAURAR (PATCH)
    @PatchMapping("/{id}/restore")
    public ResponseEntity<ClientDto> restoreClient(@PathVariable Long id) {
        ClientDto restoredClient = clientService.restoreClient(id);
        return new ResponseEntity<>(restoredClient, HttpStatus.OK);
    }
}