package com.rejj.ecommerce.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.rejj.ecommerce.service.ClientService;
import com.rejj.ecommerce.dto.UserDTO;


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
    public ResponseEntity<UserDTO> getClientById(@PathVariable Integer id) {
        UserDTO client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    /*
     * Create client
     */
    @PostMapping
    public ResponseEntity<UserDTO> createClient(@RequestBody UserDTO clientDTO) {        
        UserDTO newClient = clientService.createClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    /*
     * Update client
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateClient(@PathVariable Integer id, @RequestBody UserDTO clientDTO) {        
        UserDTO client = clientService.updateClient(id, clientDTO);
        return ResponseEntity.ok(client);
    }

    /*
     * Block client
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> blockClient(@PathVariable Integer id) {
        clientService.blockClient(id);
        return ResponseEntity.noContent().build();
    }
}
