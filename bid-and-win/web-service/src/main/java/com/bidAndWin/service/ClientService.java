package com.bidAndWin.service;

import com.bidAndWin.dto.ClientDto;
import com.bidAndWin.exceptions.ClientNotFoundException;
import com.bidAndWin.model.client.Client;
import com.bidAndWin.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDto saveClient(ClientDto clientDto) {

        // from ClientDto to Client
        Client client=ClientDto.toClient(clientDto);
        //// save client
        Client savedClient = clientRepository.save(client);

        // from Client to ClientDto
        return ClientDto.toClientDto(savedClient);
    }

    public Client findClientById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client with id: " + clientId + " not found"));
    }

    public List<ClientDto> findAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(ClientDto::toClientDto)
                .toList();
    }


    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    public ClientDto updateClient(Long clientId, ClientDto clientDto) {
        // find client by id
        Client clientById = findClientById(clientId);

        clientById.setEmail(clientDto.getEmail());
        clientById.setFirstName(clientDto.getFirstName());
        clientById.setLastName(clientDto.getLastName());
        clientById.setPhone(clientDto.getPhone());
        clientById.setGender(clientDto.getGender());
        clientById.setDateOfBirth(clientDto.getDateOfBirth());

        Client save = clientRepository.save(clientById);
        var updatedClientDto = new ClientDto();
        updatedClientDto.setClientId(save.getClientId());
        updatedClientDto.setEmail(save.getEmail());
        return updatedClientDto;
    }


    ////DataIntegrityViolationException
}
