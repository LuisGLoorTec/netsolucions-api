package com.netsolucions.netsolucions_api.contract.service;

import com.netsolucions.netsolucions_api.client.entity.Client;
import com.netsolucions.netsolucions_api.client.repository.ClientRepository;
import com.netsolucions.netsolucions_api.contract.dto.ContractCreateDto;
import com.netsolucions.netsolucions_api.contract.dto.ContractDto;
import com.netsolucions.netsolucions_api.contract.entity.Contract;
import com.netsolucions.netsolucions_api.contract.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final ClientRepository clientRepository;

    // eh aqui los dos repositorios porque necesitamos hablar con ambas tablas
    public ContractService(ContractRepository contractRepository, ClientRepository clientRepository) {
        this.contractRepository = contractRepository;
        this.clientRepository = clientRepository;
    }

    public ContractDto createContract(ContractCreateDto createDto) {
        //buscamos si el cliente existe usando el ID que nos mandaron
        Client clienteEncontrado = clientRepository.findById(createDto.getClientId())
                .orElseThrow(() -> new RuntimeException("Error: Cliente no encontrado con ID " + createDto.getClientId()));

        // creamos la caja vacía del Contrato y la llenamos con los datos del DTO
        Contract contract = new Contract();
        contract.setIdentificador(createDto.getIdentificador());
        contract.setFechaInicio(createDto.getFechaInicio());
        contract.setFechaFin(createDto.getFechaFin());
        contract.setMontoMensual(createDto.getMontoMensual());
        contract.setEstado(createDto.getEstado());

        // le asignamos el cliente real al contrato
        contract.setCliente(clienteEncontrado);

        // guardamos el contrato en la base de datos
        Contract savedContract = contractRepository.save(contract);

        // armamos el DTO de respuesta para Postman
        ContractDto responseDto = new ContractDto();
        responseDto.setId(savedContract.getId());
        responseDto.setIdentificador(savedContract.getIdentificador());
        responseDto.setFechaInicio(savedContract.getFechaInicio());
        responseDto.setFechaFin(savedContract.getFechaFin());
        responseDto.setMontoMensual(savedContract.getMontoMensual());
        responseDto.setEstado(savedContract.getEstado());
        responseDto.setClientId(savedContract.getCliente().getId());

        return responseDto;
    }

    // obtener todos los contratos
    public List<ContractDto> getAllContracts() {
        // buscamos todos los contratos en la base de datos
        List<Contract> contratos = contractRepository.findAll();

        // convertimos cada Contract a un ContractDto
        return contratos.stream().map(contract -> {
            ContractDto dto = new ContractDto();
            dto.setId(contract.getId());
            dto.setIdentificador(contract.getIdentificador());
            dto.setFechaInicio(contract.getFechaInicio());
            dto.setFechaFin(contract.getFechaFin());
            dto.setMontoMensual(contract.getMontoMensual());
            dto.setEstado(contract.getEstado());
            dto.setClientId(contract.getCliente().getId()); // Sacamos el ID del cliente
            return dto;
        }).toList();
    }
    //esto es basicamente un traductor de la base de datos al postman
    public ContractDto toDto(Contract contract) {
        ContractDto dto = new ContractDto();
        dto.setId(contract.getId());
        dto.setIdentificador(contract.getIdentificador());
        dto.setFechaInicio(contract.getFechaInicio());
        dto.setFechaFin(contract.getFechaFin());
        dto.setMontoMensual(contract.getMontoMensual());
        dto.setEstado(contract.getEstado());
        dto.setClientId(contract.getCliente().getId());
        return dto;
    }

    // GET por ID
    public ContractDto getContractById(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Contrato no encontrado"));
        return toDto(contract);
    }

    // ACTUALIZAR PUT
    public ContractDto updateContract(Long id, ContractCreateDto updateDto) {
        // Buscamos el contrato
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Contrato no encontrado"));

        // Buscamos si el cliente nuevo (o el mismo) existe
        Client cliente = clientRepository.findById(updateDto.getClientId())
                .orElseThrow(() -> new RuntimeException("Error: Cliente no encontrado"));

        // Actualizamos los datos
        contract.setIdentificador(updateDto.getIdentificador());
        contract.setFechaInicio(updateDto.getFechaInicio());
        contract.setFechaFin(updateDto.getFechaFin());
        contract.setMontoMensual(updateDto.getMontoMensual());
        contract.setEstado(updateDto.getEstado());
        contract.setCliente(cliente);

        // Guardamos y convertimos a DTO
        Contract updatedContract = contractRepository.save(contract);
        return toDto(updatedContract);
    }

    // BORRADO LÓGICO (DELETE)
    public void deleteContract(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Contrato no encontrado"));

        contract.setEstado(com.netsolucions.netsolucions_api.contract.enums.ContractStatus.CANCELADO);

        contractRepository.save(contract);
    }
    // RESTAURAR CONTRATO (PATCH)
    public ContractDto restoreContract(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Contrato no encontrado"));

        contract.setEstado(com.netsolucions.netsolucions_api.contract.enums.ContractStatus.ACTIVO);

        Contract updatedContract = contractRepository.save(contract);
        return toDto(updatedContract);
    }
}



