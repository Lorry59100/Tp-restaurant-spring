package TP.restaurant.Service;

import TP.restaurant.Entity.Client;
import TP.restaurant.Repository.IClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    private final IClientRepository clientRepository;

    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client clientDetails) throws Exception {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isPresent()) {
            Client client = optionalClient.get();
            if (clientDetails.getName() != null) {
                client.setName(clientDetails.getName());
            }
            if (clientDetails.getEmail() != null) {
                client.setEmail(clientDetails.getEmail());
            }
            return clientRepository.save(client);
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
