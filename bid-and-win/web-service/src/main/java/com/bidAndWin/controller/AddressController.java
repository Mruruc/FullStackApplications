package com.bidAndWin.controller;

import com.bidAndWin.dto.AddressDto;
import com.bidAndWin.model.client.Address;
import com.bidAndWin.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/address")

public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(value = "/{clientId}", produces = "application/json")
    public ResponseEntity<AddressDto> getAddress(@PathVariable Long clientId) {
        return new ResponseEntity<>(addressService.getAddress(clientId), HttpStatus.OK);
    }

    @PostMapping(value = "/{clientId}")
    public ResponseEntity<Map<String, String>> createAddress(@PathVariable Long clientId, @RequestBody Address address) {
        addressService.saveAddress(clientId, address);
        return new ResponseEntity<>(Map.of("message", "CREATED"), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{clientId}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Long clientId, @RequestBody Address address) {
        return new ResponseEntity<>(addressService.updateAddress(clientId, address), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{clientId}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long clientId) {
        addressService.deleteAddress(clientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
