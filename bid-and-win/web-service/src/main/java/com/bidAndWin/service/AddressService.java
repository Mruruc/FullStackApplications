package com.bidAndWin.service;

import com.bidAndWin.dto.AddressDto;
import com.bidAndWin.model.client.Address;
import com.bidAndWin.model.client.Client;
import com.bidAndWin.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final ClientService  clientService;
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(ClientService clientService, AddressRepository addressRepository) {
        this.clientService = clientService;
        this.addressRepository = addressRepository;
    }

    public void saveAddress(Long clientId,Address address){
        Client clientById = clientService.findClientById(clientId);
        address.setClient(clientById);
        addressRepository.save(address);
    }

    public AddressDto getAddress(Long clientId){
        return AddressDto.toAddressDto(addressRepository.findByClient_ClientId(clientId));
    }

    public AddressDto updateAddress(Long clientId, Address address) {
        address.setClient(clientService.findClientById(clientId));
        return AddressDto.toAddressDto(addressRepository.save(address));
    }

    public void deleteAddress(Long clientId) {
        addressRepository.deleteByClient_ClientId(clientId);
    }
}
