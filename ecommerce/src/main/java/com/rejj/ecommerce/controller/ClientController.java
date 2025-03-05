package com.rejj.ecommerce.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.rejj.ecommerce.service.ClientService;
import com.rejj.ecommerce.dto.ClientDTO;


@RestController
@RequestMapping("/api/ecommerce/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    /*
     * Get client by its id
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        ClientDTO client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    /*
     * Create client
     */
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {        
        ClientDTO newClient = clientService.createClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    /*
     * Update client
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {        
        ClientDTO client = clientService.updateClient(id, clientDTO);
        return ResponseEntity.ok(client);
    }

    /*
     * Delete client
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
