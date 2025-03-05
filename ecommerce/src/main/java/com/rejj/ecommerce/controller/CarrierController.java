package com.rejj.ecommerce.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.rejj.ecommerce.service.CarrierService;
import com.rejj.ecommerce.model.Carrier;

@RestController
@RequestMapping("/api/ecommerce/carriers")
public class CarrierController {

    private final CarrierService carrierService;

    public CarrierController(CarrierService carrierService){
        this.carrierService = carrierService;
    }

        /*
     * Get carrier by its id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Carrier> getCarrierById(@PathVariable Integer id) {
        Carrier client = carrierService.getCarrierById(id);
        return ResponseEntity.ok(client);
    }

    /*
     * Create carrier
     */
    @PostMapping
    public ResponseEntity<Carrier> createCarrier(@RequestBody Carrier carrierDTO) {        
        Carrier newCarrier = carrierService.createCarrier(carrierDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCarrier);
    }

    /*
     * Update carrier
     */
    @PutMapping("/{id}")
    public ResponseEntity<Carrier> updateCarrier(@PathVariable Integer id, @RequestBody Carrier carrierDTO) {        
        Carrier carrier = carrierService.updateCarrier(id, carrierDTO);
        return ResponseEntity.ok(carrier);
    }

    /*
     * Delete carrier
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        carrierService.deleteCarrier(id);
        return ResponseEntity.noContent().build();
    }

}
