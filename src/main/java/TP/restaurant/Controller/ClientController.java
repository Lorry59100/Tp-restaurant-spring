package TP.restaurant.Controller;

import TP.restaurant.Entity.Client;
import TP.restaurant.Service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        Client createdClient = clientService.addClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) throws Exception {
        Client updatedClient = clientService.updateClient(id, client);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) throws Exception {
        String result = clientService.deleteClient(id);
        return ResponseEntity.ok(result);
    }
}
