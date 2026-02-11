package com.netsolucions.netsolucions_api.contract.controller;

import com.netsolucions.netsolucions_api.contract.dto.ContractCreateDto;
import com.netsolucions.netsolucions_api.contract.dto.ContractDto;
import com.netsolucions.netsolucions_api.contract.service.ContractService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contracts") // Esta es la URL donde escuchará este controlador
public class ContractController {

    private final ContractService contractService;

    // nuestro Service
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    // endpoint para CREAR un contrato (POST)
    @PostMapping
    public ResponseEntity<ContractDto> createContract(@Valid @RequestBody ContractCreateDto createDto) {
        // le pasamos los datos al Service para que haga la magia
        ContractDto newContract = contractService.createContract(createDto);

        // devolvemos el contrato creado y un código 201 (Creado exitosamente)
        return new ResponseEntity<>(newContract, HttpStatus.CREATED);
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<ContractDto>> getAllContracts() {
        List<ContractDto> contracts = contractService.getAllContracts();
        return new ResponseEntity<>(contracts, HttpStatus.OK); // Devuelve un 200 OK
    }
    // GET: Buscar contrato por ID
    @GetMapping("/{id}")
    public ResponseEntity<ContractDto> getContractById(@PathVariable Long id) {
        ContractDto contract = contractService.getContractById(id);
        return new ResponseEntity<>(contract, HttpStatus.OK);
    }

    // Actualizar contrato
    @PutMapping("/{id}")
    public ResponseEntity<ContractDto> updateContract(@PathVariable Long id, @Valid @RequestBody ContractCreateDto updateDto) {
        ContractDto updatedContract = contractService.updateContract(id, updateDto);
        return new ResponseEntity<>(updatedContract, HttpStatus.OK);
    }

    // Borrado Lógico (Cambiar estado a CANCELADO)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
    // PATCH: Restaurar / Reactivar Contrato
    @PatchMapping("/{id}/restore")
    public ResponseEntity<ContractDto> restoreContract(@PathVariable Long id) {
        ContractDto restoredContract = contractService.restoreContract(id);
        return new ResponseEntity<>(restoredContract, HttpStatus.OK);
    }
}

