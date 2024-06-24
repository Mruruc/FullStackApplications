package com.bidAndWin.dto;

import com.bidAndWin.model.client.Client;
import com.bidAndWin.model.client.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ClientDto {
    private Long clientId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String email;
    private String phone;
    private AddressDto address;
    private UserDTO user;


    public ClientDto() {
    }

    public static ClientDto toClientDto(Client client) {
        ClientDto clientDto = new ClientDto();

        clientDto.setClientId(client.getClientId());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setDateOfBirth(client.getDateOfBirth());
        clientDto.setGender(client.getGender());
        clientDto.setEmail(client.getEmail());
        clientDto.setPhone(client.getPhone());
        clientDto.setAddress(AddressDto.toAddressDto(client.getAddress()));
        clientDto.setUser(UserDTO.toUserDto(client.getUser()));
        return clientDto;

    }


    public static Client toClient(ClientDto clientDto) {
        if(clientDto == null){
            return null;
        }
        Client client = new Client();

        client.setClientId(clientDto.getClientId());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setDateOfBirth(clientDto.getDateOfBirth());
        client.setGender(clientDto.getGender());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setAddress(AddressDto.toAddress(clientDto.getAddress(),client));
       // client.setUser(UserDTO.toUser(clientDto.getUser()));

        return client;
    }
}
