package TP.restaurant.Controller;
import TP.restaurant.Dto.ClientDto;
import TP.restaurant.Service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpServerErrorException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ClientControllerTest.class)
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addClient_shouldReturnCreatedClientWithStatus201() {
        //Prépa données
        final String name = "createdClient";
        final String email = "client@created.com";
        final ClientDto client = new ClientDto(email, name);
        //Prépa mock
        when(clientService.addClient(client)).thenReturn(client);
        //Exe
        final ResponseEntity<ClientDto> response = clientController.addClient(client);
        //Vérif
        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(email, response.getBody().getEmail());
        assertEquals(name, response.getBody().getName());
    }

    @Test
    void addClient_shouldReturnNotCreatedClientWithStatus500() {
        // Prépa données
        final String name = null;
        final String email = null;
        final ClientDto willNotBeCreatedClient = new ClientDto(email, name);
        // Prépa mock
        when(clientService.addClient(willNotBeCreatedClient)).thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"));
        // Exe
        try {
            clientController.addClient(willNotBeCreatedClient);
        } catch (HttpServerErrorException e) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getStatusCode());
            assertEquals("500 Internal Server Error", e.getMessage());
        }
    }

    @Test
    void updateClient_shouldReturnUpdatedClientWithStatus200() throws Exception {
        // Prepare data
        final String initialEmail = "mail@toUpdate.com";
        final String initialName = "nameToUpdate";
        final Long id = 200L;
        final ClientDto clientToUpdate = new ClientDto(initialEmail, initialName);

        final String updatedEmail = "updatedEmail";
        final String updatedName = "updatedName";
        ClientDto updatedClientDto = new ClientDto(updatedEmail, updatedName);

        // Prepare Mock
        when(clientService.updateClient(id, clientToUpdate)).thenReturn(updatedClientDto);

        // Execute
        mockMvc.perform(put("/clients/{id}", id)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(clientToUpdate)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedClientDto)));

        // Verify
        verify(clientService, times(1)).updateClient(id, clientToUpdate);
    }
}