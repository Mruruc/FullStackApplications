package com.bidAndWin.controller;

import com.bidAndWin.dto.ClientDto;
import com.bidAndWin.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/clients")
public class ClientController  {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientDto> getAll() {
        return clientService.findAllClients();
    }


    @GetMapping(value = "/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientDto getClientById(@PathVariable Long clientId) {
        return ClientDto.toClientDto(clientService.findClientById(clientId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto clientDto) {
        return new ResponseEntity<>(
                clientService.saveClient(clientDto),
                HttpStatus.CREATED
        );
    }


    @PutMapping(value = "/{clientId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long clientId, @RequestBody ClientDto client) {
        return new ResponseEntity<>(
                clientService.updateClient(clientId, client),
                HttpStatus.OK
        );
    }


    @DeleteMapping("/{clientId}")
    public ResponseEntity<Map<String, String>> deleteClient(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
        return new ResponseEntity<>(Map.of("message", "Successful"), HttpStatus.OK);
    }
}
