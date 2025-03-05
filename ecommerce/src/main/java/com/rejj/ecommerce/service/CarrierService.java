package com.rejj.ecommerce.service;

import org.springframework.stereotype.Service;

import com.rejj.ecommerce.repository.CarrierRepository;
import com.rejj.ecommerce.model.Carrier;

@Service
public class CarrierService {

    private final CarrierRepository carrierRepository;

    public CarrierService(CarrierRepository carrierRepository){
        this.carrierRepository = carrierRepository;
    }

    /*
     * CRUD
     */

    /*
     * Get carrier by its id
     */
    public Carrier getCarrierById(Long id){
        Carrier carrier = carrierRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Transportista no encontrado"));

        return carrier;
    }
    

    /*
     * Create carrier
     */
    public Carrier createCarrier(Carrier carrier){
        
        Carrier carrierTo = new Carrier();
        carrierTo.setCarrierName(carrier.getCarrierName());

        carrierRepository.save(carrierTo);
        return getCarrierById(carrierTo.getId());
    }

    /*
     * Update carrier
     */
    public Carrier updateCarrier(Long id, Carrier carrier){
        
        Carrier carrierTo = carrierRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Transportista no encontrado"));
;
        carrierTo.setCarrierName(carrier.getCarrierName());

        carrierRepository.save(carrierTo);
        return getCarrierById(carrierTo.getId());
    }

    /*
     * Delete carrier
     */
    public void deleteCarrier(Long id){
        carrierRepository.deleteById(id);
    }


}
