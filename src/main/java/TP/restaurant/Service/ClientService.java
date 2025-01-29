package TP.restaurant.Service;

import TP.restaurant.Dto.ClientDto;
import TP.restaurant.Entity.Client;
import TP.restaurant.Exception.ClientNotFoundException;
import TP.restaurant.Mapper.ClientMapper;
import TP.restaurant.Repository.IClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;

@Service
public class ClientService {
    private final IClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(IClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public ClientDto addClient(ClientDto clientDto) {
        try {
            Client client = clientMapper.dtoToEntity(clientDto);
            Client savedClient = clientRepository.save(client);
            return clientMapper.entityToDto(savedClient);
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    public ClientDto updateClient(Long id, ClientDto clientDetails){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isPresent()) {
            Client client = optionalClient.get();
            if (clientDetails.getName() != null) {
                client.setName(clientDetails.getName());
            }
            if (clientDetails.getEmail() != null) {
                client.setEmail(clientDetails.getEmail());
            }
            Client updatedClient = clientRepository.save(client);
            return clientMapper.entityToDto(updatedClient);
        } else {
            throw new ClientNotFoundException("Client inconnu");
        }
    }

    public String deleteClient(Long id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            clientRepository.delete(client);
            return "Client supprimé";
        } else {
            throw new ClientNotFoundException("Client inconnu");
        }
    }
}
