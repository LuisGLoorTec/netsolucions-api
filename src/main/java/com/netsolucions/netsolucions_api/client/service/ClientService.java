package com.netsolucions.netsolucions_api.client.service;

import com.netsolucions.netsolucions_api.client.dto.ClientCreateDto;
import com.netsolucions.netsolucions_api.client.dto.ClientDto;
import com.netsolucions.netsolucions_api.client.entity.Client;
import com.netsolucions.netsolucions_api.client.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    // Listar todos los clientes
    public List<Client> listar() {
        return repository.findAll();
    }

    // Buscar un cliente por ID
    public Client obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    // Transforma los datos externos (DTO) en una Entidad para poder guardarla en la base de datos.
    public Client crearDesdeDto(ClientCreateDto dto) {
        Client client = new Client();
        client.setNombre(dto.getNombre());
        client.setCedula(dto.getCedula());
        client.setCorreo(dto.getCorreo());
        client.setTelefono(dto.getTelefono());
        client.setDireccion(dto.getDireccion());
        return repository.save(client);
    }

    public ClientDto toDto(Client client) {
        String estadoTexto = client.isActivo() ? "ACTIVO" : "INACTIVO";

        return new ClientDto(
                client.getId(),
                client.getNombre(),
                client.getCedula(),
                client.getCorreo(),
                client.getTelefono(),
                client.getDireccion(),
                estadoTexto
        );
    }
    // ACTUALIZAR (PUT)
    public ClientDto updateClient(Long id, ClientCreateDto updateDto) {
        // Buscamos si existe (usando 'repository')
        Client client = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Cliente no encontrado"));

        // Actualizamos los datos
        client.setNombre(updateDto.getNombre());
        client.setCedula(updateDto.getCedula());
        client.setCorreo(updateDto.getCorreo());
        client.setTelefono(updateDto.getTelefono());
        client.setDireccion(updateDto.getDireccion());

        // Guardamos los cambios
        Client updatedClient = repository.save(client);

        // Devolvemos el DTO actualizado
        ClientDto responseDto = new ClientDto();
        responseDto.setId(updatedClient.getId());
        responseDto.setNombre(updatedClient.getNombre());
        responseDto.setCedula(updatedClient.getCedula());
        responseDto.setCorreo(updatedClient.getCorreo());
        responseDto.setTelefono(updatedClient.getTelefono());
        responseDto.setDireccion(updatedClient.getDireccion());
        responseDto.setEstado(updatedClient.isActivo() ? "ACTIVO" : "INACTIVO");

        return responseDto;
    }

    // BORRADO LÓGICO (DELETE)
    public void deleteClient(Long id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Cliente no encontrado"));

        client.setActivo(false);

        repository.save(client);
    }
    // RESTAURAR CLIENTE (PATCH)
    public ClientDto restoreClient(Long id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Cliente no encontrado"));

        client.setActivo(true);

        Client updatedClient = repository.save(client);
        return toDto(updatedClient);
    }
}