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
    public Carrier getCarrierById(Integer id){
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
    public Carrier updateCarrier(Integer id, Carrier carrier){
        
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
    public void deleteCarrier(Integer id){
        carrierRepository.deleteById(id);
    }


}
