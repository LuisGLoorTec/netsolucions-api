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

    // Convierte la Entidad de la base de datos a un objeto de respuesta (DTO) para enviarlo al cliente.
    public ClientDto toDto(Client client) {
        return new ClientDto(
                client.getId(),
                client.getNombre(),
                client.getCedula(),
                client.getCorreo(),
                client.getTelefono(),
                client.getDireccion()
        );
    }
}