package com.bidAndWin.dto;

import com.bidAndWin.model.client.Address;
import com.bidAndWin.model.client.Client;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private Long addressId;
    private String country;
    private String city;
    private String zipCode;
    private String street;
    private Integer aptOrHouseNumber;
    private Long clientId;

    public AddressDto() {
    }


    public static AddressDto toAddressDto(Address address) {
        if (address == null) {
            return null;
        }
        AddressDto addressDto = new AddressDto();
        addressDto.setAddressId(address.getAddressId());
        addressDto.setCountry(address.getCountry());
        addressDto.setCity(address.getCity());
        addressDto.setZipCode(address.getZipCode());
        addressDto.setStreet(address.getStreet());
        addressDto.setAptOrHouseNumber(address.getAptOrHouseNumber());
        addressDto.setClientId(address.getClient().getClientId());
        return addressDto;

    }

    public static Address toAddress(AddressDto addressDto, Client client) {
        if(addressDto == null){
            return null;
        }
        Address address = new Address();
        address.setAddressId(addressDto.getAddressId());
        address.setCountry(addressDto.getCountry());
        address.setCity(addressDto.getCity());
        address.setZipCode(addressDto.getZipCode());
        address.setStreet(addressDto.getStreet());
        address.setAptOrHouseNumber(addressDto.getAptOrHouseNumber());
        if(client != null) {
            address.setClient(client);
        }
        return address;
    }
}
