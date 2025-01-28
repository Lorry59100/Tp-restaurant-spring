package TP.restaurant.Mapper;

import TP.restaurant.Dto.ClientDto;
import TP.restaurant.Entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDto entityToDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setEmail(client.getEmail());
        clientDto.setName(client.getName());
        return clientDto;
    }

    public Client dtoToEntity(ClientDto clientDto) {
        Client client = new Client();
        client.setEmail(clientDto.getEmail());
        client.setName(clientDto.getName());
        return client;
    }
}
