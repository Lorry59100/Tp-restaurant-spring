package TP.restaurant.Service;

import TP.restaurant.Dto.ClientDto;
import TP.restaurant.Entity.Client;
import TP.restaurant.Mapper.ClientMapper;
import TP.restaurant.Repository.IClientRepository;
import org.springframework.stereotype.Service;

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
        Client client = clientMapper.dtoToEntity(clientDto);
        Client savedClient = clientRepository.save(client);
        return clientMapper.entityToDto(savedClient);
    }

    public ClientDto updateClient(Long id, ClientDto clientDetails) throws Exception {
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
            throw new Exception("Client inconnu");
        }
    }

    public String deleteClient(Long id) throws Exception {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            clientRepository.delete(client);
            return "Client supprimé";
        } else {
            throw new Exception("Client inconnu");
        }
    }
}
